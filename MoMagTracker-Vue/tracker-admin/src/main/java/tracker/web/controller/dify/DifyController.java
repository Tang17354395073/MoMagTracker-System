package tracker.web.controller.dify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tracker.common.core.controller.BaseController;
import tracker.common.core.domain.AjaxResult;
import tracker.system.service.IDifyAuthService;

import java.util.HashMap;
import java.util.Map;

/**
 * Dify接口控制器
 */
@RestController
@RequestMapping("/dify")
public class DifyController extends BaseController {

    @Autowired
    private IDifyAuthService difyAuthService;

    /**
     * 获取Dify访问令牌
     */
    @GetMapping("/getAutoToken")
    public AjaxResult getAutoToken() {
        try {
            Map<String, String> token = difyAuthService.getAutoToken();
            return success(token);
        } catch (Exception e) {
            return error("获取Dify令牌失败: " + e.getMessage());
        }
    }

    /**
     * 获取Dify应用列表 - 对应"应用创建"菜单
     * 路由地址改为: /dify/applicationCreation
     */
    @GetMapping("/applicationCreation")
    public AjaxResult getApps(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit,
            @RequestParam(value = "is_created_by_me", defaultValue = "false") boolean isCreatedByMe,
            @RequestParam(value = "name", defaultValue = "") String name) {

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("page", page);
            params.put("limit", limit);
            params.put("is_created_by_me", isCreatedByMe);
            params.put("name", name);

            Map<String, Object> result = difyAuthService.getApps(params);
            return success(result);
        } catch (Exception e) {
            return error("获取Dify应用列表失败: " + e.getMessage());
        }
    }

    /**
     * 获取Dify数据集 - 对应"数据管理"菜单
     * 路由地址改为: /dify/knowledgeManagement
     */
    @GetMapping("/knowledgeManagement")
    public AjaxResult getDatasets(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("page", page);
            params.put("limit", limit);

            Map<String, Object> result = difyAuthService.getDatasets(params);
            return success(result);
        } catch (Exception e) {
            return error("获取Dify数据集失败: " + e.getMessage());
        }
    }

    /**
     * 获取Dify工具列表 - 对应"工具调用"菜单
     * 路由地址改为: /dify/toolInvoker
     */
    @GetMapping("/toolInvoker")
    public AjaxResult getTools(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit,
            @RequestParam(value = "name", defaultValue = "") String name) {

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("page", page);
            params.put("limit", limit);
            params.put("name", name);

            Map<String, Object> result = difyAuthService.getTools(params);
            return success(result);
        } catch (Exception e) {
            return error("获取Dify工具列表失败: " + e.getMessage());
        }
    }
}