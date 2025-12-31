package tracker.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tracker.system.config.PythonScriptConfig;
import tracker.system.service.IVideoProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;

@Service
public class VideoProcessServiceImpl implements IVideoProcessService {

    private static final Logger log = LoggerFactory.getLogger(VideoProcessServiceImpl.class);

    private final ExecutorService executorService = Executors.newFixedThreadPool(3);
    private final ConcurrentHashMap<String, Double> progressMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> taskStatusMap = new ConcurrentHashMap<>();
    private final String UPLOAD_DIR = "uploads/video/";
    private final String OUTPUT_DIR = "output/video/";

    @Autowired
    private PythonScriptConfig pythonConfig;

    @Autowired
    private PythonScriptExecutor pythonExecutor;

    @PostConstruct
    public void init() {
        try {
            new File(UPLOAD_DIR).mkdirs();
            new File(OUTPUT_DIR).mkdirs();
            log.info("视频处理服务初始化完成，上传目录：{}，输出目录：{}", UPLOAD_DIR, OUTPUT_DIR);

            // 检查Python环境
            if (pythonConfig.isEnabled()) {
                boolean pythonAvailable = pythonExecutor.checkPythonEnvironment();
                log.info("Python环境检查: {}", pythonAvailable ? "可用" : "不可用");

                if (!pythonAvailable) {
                    log.warn("Python环境不可用，部分功能可能无法正常工作");
                }
            } else {
                log.info("Python脚本功能已禁用");
            }

        } catch (Exception e) {
            log.error("初始化视频目录失败", e);
        }
    }

    @Override
    public String processVideo(MultipartFile videoFile,
                               String algorithm,
                               Double amplification,
                               Boolean temporalFilter,
                               Boolean otherSettings,
                               Double upperThreshold,
                               Double lowerThreshold,
                               Double spatialSigma) throws Exception {

        String taskId = UUID.randomUUID().toString();
        String originalFilename = videoFile.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        String inputPath = UPLOAD_DIR + taskId + extension;
        String outputPath = OUTPUT_DIR + taskId + "_processed.mp4";

        // 保存上传的文件
        Path uploadPath = Paths.get(inputPath);
        Files.copy(videoFile.getInputStream(), uploadPath);

        // 提交处理任务
        executorService.submit(() -> {
            try {
                processVideoTask(taskId, inputPath, outputPath, algorithm,
                        amplification, temporalFilter, otherSettings,
                        upperThreshold, lowerThreshold, spatialSigma);
            } catch (Exception e) {
                log.error("视频处理任务失败", e);
                progressMap.put(taskId, -1.0);
                taskStatusMap.put(taskId, "FAILED");
            }
        });

        log.info("视频处理任务已提交，任务ID：{}，算法：{}，放大倍数：{}，时域滤波：{}",
                taskId, algorithm, amplification, temporalFilter);

        taskStatusMap.put(taskId, "PROCESSING");
        return taskId;
    }

    private void processVideoTask(String taskId, String inputPath, String outputPath,
                                  String algorithm, Double amplification,
                                  Boolean temporalFilter, Boolean otherSettings,
                                  Double upperThreshold, Double lowerThreshold,
                                  Double spatialSigma) {

        progressMap.put(taskId, 0.0);

        // 根据算法类型选择处理方式
        if (pythonConfig.hasScript(algorithm)) {
            // 使用Python脚本处理
            processWithPythonScript(taskId, algorithm, inputPath, outputPath,
                    amplification, temporalFilter,
                    upperThreshold, lowerThreshold, spatialSigma);
        } else {
            // 使用Java模拟处理
            processWithJavaSimulation(taskId, algorithm, amplification,
                    temporalFilter, otherSettings,
                    upperThreshold, lowerThreshold,
                    spatialSigma);
        }
    }

    private void processWithPythonScript(String taskId, String algorithm,
                                         String inputPath, String outputPath,
                                         Double amplification, Boolean temporalFilter,
                                         Double upperThreshold, Double lowerThreshold,
                                         Double spatialSigma) {
        try {
            log.info("开始Python脚本处理，任务ID: {}, 算法: {}, 输入: {}, 输出: {}",
                    taskId, algorithm, inputPath, outputPath);

            if (!pythonConfig.isEnabled()) {
                String errorMsg = "Python脚本功能未启用";
                log.error(errorMsg);
                progressMap.put(taskId, -1.0);
                taskStatusMap.put(taskId, "FAILED");
                return;
            }

            PythonScriptExecutor.ProgressCallback callback = new PythonScriptExecutor.ProgressCallback() {
                @Override
                public void onProgress(double progress) {
                    progressMap.put(taskId, progress);
                    log.debug("任务{}进度更新: {}%", taskId, progress);
                }

                @Override
                public void onStepStart(String stepName) {
                    log.info("任务{}步骤开始: {}", taskId, stepName);
                }

                @Override
                public void onStepComplete(String stepName) {
                    log.info("任务{}步骤完成: {}", taskId, stepName);
                }

                @Override
                public void onError(String error) {
                    log.error("任务{}处理错误: {}", taskId, error);
                    progressMap.put(taskId, -1.0);
                    taskStatusMap.put(taskId, "FAILED");
                }
            };

            // 构建参数映射
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("alpha", amplification);
            parameters.put("temporal_filter", temporalFilter);
            parameters.put("lower_threshold", lowerThreshold);
            parameters.put("upper_threshold", upperThreshold);
            parameters.put("spatial_sigma", spatialSigma);

            // 调用Python脚本执行器
            PythonScriptExecutor.ProcessResult result = pythonExecutor.executePythonScript(
                    algorithm,
                    inputPath,
                    outputPath,
                    parameters,
                    callback
            );

            if (result.isSuccess()) {
                log.info("Python脚本处理完成，任务ID: {}, 算法: {}, 输出路径: {}",
                        taskId, algorithm, result.getOutputPath());
                progressMap.put(taskId, 100.0);
                taskStatusMap.put(taskId, "COMPLETED");
            } else {
                log.error("Python脚本处理失败，任务ID: {}, 算法: {}, 错误: {}",
                        taskId, algorithm, result.getError());
                progressMap.put(taskId, -1.0);
                taskStatusMap.put(taskId, "FAILED");
            }

        } catch (Exception e) {
            log.error("Python脚本处理异常，任务ID: {}, 算法: {}", taskId, algorithm, e);
            progressMap.put(taskId, -1.0);
            taskStatusMap.put(taskId, "FAILED");
        }
    }

    private void processWithJavaSimulation(String taskId, String algorithm,
                                           Double amplification, Boolean temporalFilter,
                                           Boolean otherSettings, Double upperThreshold,
                                           Double lowerThreshold, Double spatialSigma) {
        // Java模拟处理
        try {
            log.info("开始Java模拟处理，任务ID: {}, 算法: {}, 放大倍数: {}, 时域滤波: {}",
                    taskId, algorithm, amplification, temporalFilter);

            // 模拟处理步骤
            for (int i = 0; i <= 100; i += 10) {
                Thread.sleep(1000); // 模拟处理时间
                progressMap.put(taskId, (double) i);

                switch (algorithm) {
                    case "eulerian":
                        log.debug("使用欧拉视角算法处理，阈值范围：[{}, {}]", lowerThreshold, upperThreshold);
                        break;
                    case "deeplearning":
                        log.debug("使用深度学习算法处理，空间平滑: {}", spatialSigma);
                        break;
                    default:
                        log.warn("未知算法：{}，使用默认处理", algorithm);
                }
            }

            progressMap.put(taskId, 100.0);
            taskStatusMap.put(taskId, "COMPLETED");
            log.info("Java模拟处理完成：{}", taskId);

        } catch (Exception e) {
            log.error("Java处理任务失败", e);
            progressMap.put(taskId, -1.0);
            taskStatusMap.put(taskId, "FAILED");
        }
    }

    @Override
    public Double getProgress(String taskId) {
        Double progress = progressMap.get(taskId);
        if (progress == null) {
            return 0.0;
        }

        // 如果任务失败，返回-1
        if (progress == -1.0) {
            return -1.0;
        }

        return progress;
    }

    @Override
    public void startRealtimeProcess(String algorithm, Double amplification,
                                     Boolean temporalFilter, Boolean otherSettings,
                                     Double upperThreshold, Double lowerThreshold,
                                     Double spatialSigma) {
        log.info("开始实时视频处理，算法：{}，参数：[放大倍数={}, 时域滤波={}, 上下限阈值=[{}, {}], 空间平滑={}]",
                algorithm, amplification, temporalFilter, lowerThreshold, upperThreshold, spatialSigma);
        // TODO: 实现实时处理逻辑
    }

    @Override
    public void stopRealtimeProcess() {
        log.info("停止实时视频处理");
        // TODO: 实现停止实时处理逻辑
    }

    @Override
    public File getResultFile(String taskId) throws Exception {
        String outputPath = OUTPUT_DIR + taskId + "_processed.mp4";
        File resultFile = new File(outputPath);

        if (!resultFile.exists()) {
            // 尝试其他可能的扩展名
            String[] extensions = {".mp4", ".avi", ".mov", ".mkv"};
            for (String ext : extensions) {
                resultFile = new File(OUTPUT_DIR + taskId + "_processed" + ext);
                if (resultFile.exists()) {
                    break;
                }
            }
        }

        if (!resultFile.exists()) {
            throw new FileNotFoundException("处理结果不存在，任务ID: " + taskId);
        }

        return resultFile;
    }
}