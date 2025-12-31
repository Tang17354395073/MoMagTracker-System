package tracker.system.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tracker.system.config.PythonScriptConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.*;

@Component
public class PythonScriptExecutor {

    private static final Logger log = LoggerFactory.getLogger(PythonScriptExecutor.class);

    @Autowired
    private PythonScriptConfig pythonConfig;

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * 执行Python脚本
     */
    public ProcessResult executePythonScript(
            String algorithm,
            String inputVideoPath,
            String outputVideoPath,
            Map<String, Object> parameters,
            ProgressCallback callback) {

        ProcessResult result = new ProcessResult();

        try {
            // 检查算法是否有对应的Python脚本
            if (!pythonConfig.hasScript(algorithm)) {
                String errorMsg = String.format("算法 '%s' 没有对应的Python脚本配置", algorithm);
                log.error(errorMsg);
                result.setSuccess(false);
                result.setError(errorMsg);
                return result;
            }

            // 获取脚本路径
            String scriptPath = pythonConfig.getScriptPath(algorithm);
            File scriptFile = new File(scriptPath);
            if (!scriptFile.exists()) {
                String errorMsg = String.format("Python脚本不存在: %s", scriptPath);
                log.error(errorMsg);
                result.setSuccess(false);
                result.setError(errorMsg);
                return result;
            }

            // 构建Python命令
            String[] command = buildPythonCommand(
                    scriptPath,
                    inputVideoPath,
                    outputVideoPath,
                    parameters
            );

            log.info("执行Python脚本 - 算法: {}, 脚本: {}", algorithm, scriptPath);
            log.debug("Python命令: {}", String.join(" ", command));

            // 创建进程
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true); // 合并标准错误和标准输出

            Process process = processBuilder.start();

            // 启动线程读取输出
            Future<?> outputReader = executorService.submit(() -> {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream(), "GBK"))) {

                    String line;
                    while ((line = reader.readLine()) != null) {
                        log.debug("Python输出: {}", line);
                        processOutput(line, result, callback);
                    }
                } catch (IOException e) {
                    log.error("读取Python输出时出错", e);
                }
            });

            // 等待进程完成
            int exitCode = process.waitFor();
            outputReader.get(); // 等待输出读取完成

            result.setExitCode(exitCode);

            // 处理结果
            handleProcessResult(result, outputVideoPath);

        } catch (Exception e) {
            log.error("执行Python脚本失败", e);
            result.setSuccess(false);
            result.setError("执行失败: " + e.getMessage());
        }

        return result;
    }

    private String[] buildPythonCommand(
            String scriptPath,
            String inputPath,
            String outputPath,
            Map<String, Object> parameters) {

        // 构建命令参数数组
        List<String> commandList = new ArrayList<>();
        commandList.add(pythonConfig.getExecutable());
        commandList.add(scriptPath);

        // 添加必需参数
        commandList.add("--input");
        commandList.add(inputPath);

        commandList.add("--output");
        commandList.add(outputPath);

        // 添加可选参数
        Object alpha = parameters.get("alpha");
        if (alpha != null) {
            commandList.add("--alpha");
            commandList.add(String.valueOf(alpha));
        }

        Object temporalFilter = parameters.get("temporal_filter");
        if (temporalFilter != null) {
            commandList.add("--temporal_filter");
            commandList.add(String.valueOf(temporalFilter));
        }

        Object lowerThreshold = parameters.get("lower_threshold");
        if (lowerThreshold != null) {
            commandList.add("--lower_threshold");
            commandList.add(String.valueOf(lowerThreshold));
        }

        Object upperThreshold = parameters.get("upper_threshold");
        if (upperThreshold != null) {
            commandList.add("--upper_threshold");
            commandList.add(String.valueOf(upperThreshold));
        }

        Object spatialSigma = parameters.get("spatial_sigma");
        if (spatialSigma != null) {
            commandList.add("--spatial_sigma");
            commandList.add(String.valueOf(spatialSigma));
        }

        return commandList.toArray(new String[0]);
    }

    private void processOutput(String line, ProcessResult result, ProgressCallback callback) {
        // 解析进度信息
        if (line.startsWith("PROGRESS:")) {
            String progressStr = line.substring(9).trim();
            try {
                double progress = Double.parseDouble(progressStr);
                if (callback != null) {
                    callback.onProgress(progress);
                }
            } catch (NumberFormatException e) {
                log.warn("无法解析进度: {}", progressStr);
            }
        }
        // 解析错误信息
        else if (line.startsWith("ERROR:")) {
            String error = line.substring(6).trim();
            result.setError(error);
            if (callback != null) {
                callback.onError(error);
            }
        }
        // 解析最终结果
        else if (line.startsWith("FINAL_RESULT:")) {
            String finalPath = line.substring(13).trim();
            result.setOutputPath(finalPath);
        }
        else if (line.startsWith("RESULT:")) {
            String finalPath = line.substring(7).trim();
            result.setOutputPath(finalPath);
        }
        else if (line.startsWith("PROGRESS_START:")) {
            String stepName = line.substring(15).trim();
            if (callback != null) {
                callback.onStepStart(stepName);
            }
        }
        else if (line.startsWith("PROGRESS_COMPLETE:")) {
            String stepName = line.substring(18).trim();
            if (callback != null) {
                callback.onStepComplete(stepName);
            }
        }
    }

    private void handleProcessResult(ProcessResult result, String expectedOutputPath) {
        if (result.getExitCode() == 0) {
            // 检查输出文件是否存在
            File outputFile = new File(expectedOutputPath);
            if (outputFile.exists() && outputFile.length() > 0) {
                result.setSuccess(true);
                log.info("Python脚本执行成功，输出文件: {}", expectedOutputPath);
            } else {
                result.setSuccess(false);
                String errorMsg = "输出文件未生成或为空";
                result.setError(errorMsg);
                log.error(errorMsg);
            }
        } else {
            result.setSuccess(false);
            if (result.getError() == null) {
                String errorMsg = "Python进程异常退出，退出码: " + result.getExitCode();
                result.setError(errorMsg);
            }
            log.error("Python脚本执行失败: {}", result.getError());
        }
    }

    /**
     * 检查Python环境是否可用
     */
    public boolean checkPythonEnvironment() {
        try {
            // 检查Python是否安装
            ProcessBuilder pb = new ProcessBuilder(pythonConfig.getExecutable(), "--version");
            Process process = pb.start();
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                log.error("Python环境检查失败，退出码: {}", exitCode);
                return false;
            }

            // 检查脚本是否存在
            boolean allScriptsExist = true;
            for (Map.Entry<String, String> entry : pythonConfig.getScripts().entrySet()) {
                String algorithm = entry.getKey();
                String scriptPath = entry.getValue();

                if (scriptPath != null && !scriptPath.isEmpty()) {
                    File scriptFile = new File(scriptPath);
                    if (!scriptFile.exists()) {
                        log.warn("算法 {} 的Python脚本不存在: {}", algorithm, scriptPath);
                        allScriptsExist = false;
                    } else {
                        log.info("算法 {} 的Python脚本存在: {}", algorithm, scriptPath);
                    }
                }
            }

            // 检查依赖
            ProcessBuilder depPb = new ProcessBuilder(
                    pythonConfig.getExecutable(),
                    "-c",
                    "import cv2; import numpy; import scipy; print('Dependencies OK')"
            );
            Process depProcess = depPb.start();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(depProcess.getInputStream()))) {
                String line = reader.readLine();
                log.info("Python依赖检查: {}", line);
            }
            int depExitCode = depProcess.waitFor();

            if (depExitCode != 0) {
                log.error("Python依赖检查失败，请确保已安装OpenCV, NumPy和SciPy");
                return false;
            }

            return allScriptsExist;

        } catch (Exception e) {
            log.error("检查Python环境失败", e);
            return false;
        }
    }

    /**
     * 获取算法列表
     */
    public Map<String, String> getAvailableAlgorithms() {
        return new HashMap<>(pythonConfig.getScripts());
    }

    // 进度回调接口
    public interface ProgressCallback {
        void onProgress(double progress);
        void onStepStart(String stepName);
        void onStepComplete(String stepName);
        void onError(String error);
    }

    // 处理结果类
    public static class ProcessResult {
        private boolean success;
        private String outputPath;
        private String error;
        private int exitCode;

        // getters and setters
        public boolean isSuccess() { return success; }
        public void setSuccess(boolean success) { this.success = success; }
        public String getOutputPath() { return outputPath; }
        public void setOutputPath(String outputPath) { this.outputPath = outputPath; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
        public int getExitCode() { return exitCode; }
        public void setExitCode(int exitCode) { this.exitCode = exitCode; }
    }
}