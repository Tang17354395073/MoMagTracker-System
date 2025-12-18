package tracker.system.service;

import java.util.Map;

/**
 * Dify认证服务接口
 */
public interface IDifyAuthService {
    /**
     * 获取Dify访问令牌
     */
    Map<String, String> getAutoToken();

    /**
     * 获取Dify应用列表 - 对应"应用创建"菜单
     */
    Map<String, Object> getApps(Map<String, Object> params);

    /**
     * 获取Dify数据集 - 对应"数据管理"菜单
     */
    Map<String, Object> getDatasets(Map<String, Object> params);

    /**
     * 获取Dify工具列表 - 对应"工具调用"菜单
     */
    Map<String, Object> getTools(Map<String, Object> params);
}