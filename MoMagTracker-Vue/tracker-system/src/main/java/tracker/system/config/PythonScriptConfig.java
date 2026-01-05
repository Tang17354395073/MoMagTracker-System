package tracker.system.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "python")
public class PythonScriptConfig {

    private String executable = "python3";
    private boolean enabled = true;
    private Map<String, String> scripts = new HashMap<>();
    private String deeplearningModelPath;

    // getters and setters
    /**
     * 自动添加深度学习模型路径参数
     */
    public String getDeeplearningModelPath() {
        return deeplearningModelPath;
    }

    public void setDeeplearningModelPath(String deeplearningModelPath) {
        this.deeplearningModelPath = deeplearningModelPath;
    }

    public String getExecutable() {
        return executable;
    }

    public void setExecutable(String executable) {
        this.executable = executable;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Map<String, String> getScripts() {
        return scripts;
    }

    public void setScripts(Map<String, String> scripts) {
        this.scripts = scripts;
    }

    /**
     * 根据算法名称获取脚本路径
     */
    public String getScriptPath(String algorithm) {
        return scripts.get(algorithm);
    }

    /**
     * 检查算法是否有对应的Python脚本
     */
    public boolean hasScript(String algorithm) {
        return scripts.containsKey(algorithm) && scripts.get(algorithm) != null && !scripts.get(algorithm).isEmpty();
    }

}