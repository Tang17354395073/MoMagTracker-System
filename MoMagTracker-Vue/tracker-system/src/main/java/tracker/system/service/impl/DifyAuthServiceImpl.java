package tracker.system.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tracker.common.utils.StringUtils;
import tracker.system.service.IDifyAuthService;
import tracker.common.core.redis.RedisCache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class DifyAuthServiceImpl implements IDifyAuthService {

    private static final Logger log = LoggerFactory.getLogger(DifyAuthServiceImpl.class);

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private RestTemplate restTemplate;

    // Dify配置
    @Value("${dify.api.url:http://8.141.94.106:21777}")
    private String difyApiUrl;

    @Value("${dify.api.key:}")
    private String difyApiKey;

    @Value("${dify.username:admin}")
    private String difyUsername;

    @Value("${dify.password:password}")
    private String difyPassword;

    @Override
    public Map<String, String> getAutoToken() {
        Map<String, String> result = new HashMap<>();
        String cacheKey = "dify:auth:token"; // Redis缓存键

        // 1. 首先尝试从Redis缓存获取有效令牌
        Map<String, String> cachedToken = redisCache.getCacheObject(cacheKey);
        if (cachedToken != null && cachedToken.containsKey("access_token")) {
            log.info("从缓存获取Dify令牌成功");
            return cachedToken;
        }

        log.info("开始获取Dify令牌，API URL: {}", difyApiUrl);

        // 2. 如果配置了API Key，优先使用（但注意API Key可能无法访问/console/api/）
        if (StringUtils.isNotBlank(difyApiKey)) {
            log.warn("使用API Key获取Dify令牌（注意：API Key可能无法访问管理后台）");
            result.put("access_token", difyApiKey);
            result.put("refresh_token", "fixed_refresh_token");
            // 将模拟token存入缓存，有效期55分钟
            redisCache.setCacheObject(cacheKey, result, 55, TimeUnit.MINUTES);
            return result;
        }

        // 3. 核心修改：使用Dify管理账号密码登录，获取真正的JWT令牌
        if (StringUtils.isNotBlank(difyUsername) && StringUtils.isNotBlank(difyPassword)) {
            try {
                log.info("使用用户名密码登录Dify获取令牌: {}", difyUsername);

                // 构建登录请求体
                Map<String, String> loginBody = new HashMap<>();
                loginBody.put("email", difyUsername); // Dify登录接口通常使用`email`字段
                loginBody.put("password", difyPassword);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(loginBody, headers);

                // 调用Dify登录API（常见路径为 /console/api/login）
                String loginUrl = difyApiUrl + "/console/api/login";
                log.info("调用Dify登录接口: {}", loginUrl);

                ResponseEntity<Map> response = restTemplate.postForEntity(
                        loginUrl, requestEntity, Map.class);

                if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                    Map<String, Object> responseBody = response.getBody();

                    // 解析Dify登录响应，获取JWT令牌
                    // Dify的实际响应格式是嵌套的 {"result":"success","data":{"access_token":"...","refresh_token":"..."}}
                    String resultStatus = (String) responseBody.get("result");

                    if ("success".equals(resultStatus)) {
                        // 从data字段中获取token
                        Map<String, Object> data = (Map<String, Object>) responseBody.get("data");
                        if (data != null) {
                            String accessToken = (String) data.get("access_token");
                            String refreshToken = (String) data.get("refresh_token");

                            if (StringUtils.isNotBlank(accessToken)) {
                                result.put("access_token", accessToken);
                                result.put("refresh_token", refreshToken != null ? refreshToken : "");

                                // 将真实token存入缓存，有效期55分钟（JWT通常1小时过期）
                                redisCache.setCacheObject(cacheKey, result, 55, TimeUnit.MINUTES);
                                log.info("Dify令牌获取成功，已缓存");
                                return result;
                            }
                        }
                    }

                    log.error("Dify登录响应异常，响应体: {}", response.getBody());
                    throw new RuntimeException("Dify登录失败，响应异常");
                }

                log.error("Dify登录HTTP状态码异常: {}", response.getStatusCode());
                throw new RuntimeException("Dify登录失败，HTTP状态码: " + response.getStatusCode());

            } catch (Exception e) {
                log.error("获取Dify令牌失败: ", e);
                throw new RuntimeException("获取Dify令牌失败: " + e.getMessage());
            }
        }

        // 4. 降级方案：返回模拟token（仅用于测试，无法真正访问管理后台）
        log.warn("未配置有效Dify认证信息，返回模拟token（仅用于测试，功能受限）");
        result.put("access_token", "test_access_token_" + System.currentTimeMillis());
        result.put("refresh_token", "test_refresh_token_" + System.currentTimeMillis());
        return result;
    }

    @Override
    public Map<String, Object> getApps(Map<String, Object> params) {
        try {
            log.info("获取Dify应用列表，参数: {}", params);

            // 获取token
            Map<String, String> token = getAutoToken();

            // 构建请求头
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token.get("access_token"));

            HttpEntity<String> entity = new HttpEntity<>(headers);

            // 调用Dify应用列表API - 路径保持不变
            String appsUrl = difyApiUrl + "/console/api/apps";

            // 添加查询参数
            StringBuilder urlBuilder = new StringBuilder(appsUrl);
            urlBuilder.append("?page=").append(params.getOrDefault("page", 1))
                    .append("&limit=").append(params.getOrDefault("limit", 30))
                    .append("&is_created_by_me=").append(params.getOrDefault("is_created_by_me", false))
                    .append("&name=").append(params.getOrDefault("name", ""));

            ResponseEntity<Map> response = restTemplate.exchange(
                    urlBuilder.toString(), HttpMethod.GET, entity, Map.class);

            log.info("Dify应用列表获取成功");
            return response.getBody() != null ? response.getBody() : new HashMap<>();

        } catch (Exception e) {
            log.error("获取Dify应用列表失败: ", e);
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("error", "获取Dify应用列表失败: " + e.getMessage());
            return errorResult;
        }
    }

    @Override
    public Map<String, Object> getDatasets(Map<String, Object> params) {
        try {
            log.info("获取Dify数据集");

            // 获取token
            Map<String, String> token = getAutoToken();

            // 构建请求头
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token.get("access_token"));
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            // 调用Dify数据集API - 路径保持不变
            String datasetsUrl = difyApiUrl + "/console/api/datasets";
            ResponseEntity<Map> response = restTemplate.exchange(
                    datasetsUrl, HttpMethod.GET, entity, Map.class);

            log.info("Dify数据集获取成功");
            return response.getBody() != null ? response.getBody() : new HashMap<>();

        } catch (Exception e) {
            log.error("获取Dify数据集失败: ", e);
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("error", "获取Dify数据集失败: " + e.getMessage());
            return errorResult;
        }
    }

    @Override
    public Map<String, Object> getTools(Map<String, Object> params) {
        try {
            log.info("获取Dify工具列表，参数: {}", params);

            // 获取token
            Map<String, String> token = getAutoToken();

            // 构建请求头
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token.get("access_token"));
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            // 调用Dify工具API - 路径保持不变
            String toolsUrl = difyApiUrl + "/console/api/tools";

            // 添加查询参数（如果Dify工具API支持分页）
            StringBuilder urlBuilder = new StringBuilder(toolsUrl);
            urlBuilder.append("?page=").append(params.getOrDefault("page", 1))
                    .append("&limit=").append(params.getOrDefault("limit", 30));

            // 可以根据需要添加更多查询参数
            if (params.containsKey("name")) {
                urlBuilder.append("&name=").append(params.get("name"));
            }

            ResponseEntity<Map> response = restTemplate.exchange(
                    urlBuilder.toString(), HttpMethod.GET, entity, Map.class);

            log.info("Dify工具列表获取成功");
            return response.getBody() != null ? response.getBody() : new HashMap<>();

        } catch (Exception e) {
            log.error("获取Dify工具列表失败: ", e);
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("error", "获取Dify工具列表失败: " + e.getMessage());
            return errorResult;
        }
    }
}