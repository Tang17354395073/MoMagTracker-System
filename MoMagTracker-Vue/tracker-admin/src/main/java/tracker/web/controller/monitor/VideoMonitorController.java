package tracker.web.controller.monitor;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tracker.common.annotation.Anonymous;
import tracker.common.core.controller.BaseController;
import tracker.common.core.domain.R;
import tracker.system.service.IVideoProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@Api(tags = "视频监控处理接口")
@RestController
@RequestMapping("/video")
public class VideoMonitorController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(VideoMonitorController.class);

    @Resource
    private IVideoProcessService videoProcessService;

    @ApiOperation("处理视频")
    @PostMapping("/process")
    public R<Object> processVideo(
            @ApiParam(value = "视频文件", required = true) @RequestParam("video") MultipartFile videoFile,
            @ApiParam(value = "算法类型", required = true) @RequestParam("algorithm") String algorithm,
            @ApiParam(value = "放大倍数", required = true) @RequestParam("amplification") Double amplification,
            @ApiParam(value = "时域滤波开关", required = true) @RequestParam("temporalFilter") Boolean temporalFilter,
            @ApiParam(value = "其他设置开关", required = true) @RequestParam("otherSettings") Boolean otherSettings,
            @ApiParam(value = "上限阈值", required = true) @RequestParam("upperThreshold") Double upperThreshold,
            @ApiParam(value = "下限阈值", required = true) @RequestParam("lowerThreshold") Double lowerThreshold,
            @ApiParam(value = "空间平滑参数", required = true) @RequestParam("spatialSigma") Double spatialSigma) {

        try {
            // 验证参数
            if (videoFile.isEmpty()) {
                return R.fail("请上传视频文件");
            }

            if (amplification < 1 || amplification > 100) {
                return R.fail("放大倍数必须在1-100之间");
            }

            if (upperThreshold < 0 || upperThreshold > 1) {
                return R.fail("上限阈值必须在0-1之间");
            }

            if (lowerThreshold < 0 || lowerThreshold > 0.1) {
                return R.fail("下限阈值必须在0-0.1之间");
            }

            if (spatialSigma < 0 || spatialSigma > 5) {
                return R.fail("空间平滑参数必须在0-5之间");
            }

            String taskId = videoProcessService.processVideo(
                    videoFile,
                    algorithm,
                    amplification,
                    temporalFilter,
                    otherSettings,
                    upperThreshold,
                    lowerThreshold,
                    spatialSigma
            );
            return R.ok(taskId);
        } catch (Exception e) {
            log.error("视频处理失败", e);
            return R.fail("视频处理失败：" + e.getMessage());
        }
    }

    @ApiOperation("获取处理进度")
    @GetMapping("/progress/{taskId}")
    public R<Object> getProgress(@ApiParam(value = "任务ID", required = true) @PathVariable String taskId) {
        try {
            Double progress = videoProcessService.getProgress(taskId);
            if (progress == -1.0) {
                return R.fail("任务处理失败");
            }
            return R.ok(progress);
        } catch (Exception e) {
            log.error("获取进度失败", e);
            return R.fail("获取进度失败：" + e.getMessage());
        }
    }

    @ApiOperation("开始实时处理")
    @PostMapping("/realtime/start")
    public R<Object> startRealtimeProcess(@RequestBody RealtimeParams params) {
        try {
            videoProcessService.startRealtimeProcess(
                    params.getAlgorithm(),
                    params.getAmplification(),
                    params.getTemporalFilter(),
                    params.getOtherSettings(),
                    params.getUpperThreshold(),
                    params.getLowerThreshold(),
                    params.getSpatialSigma()
            );
            return R.ok("实时处理已启动");
        } catch (Exception e) {
            log.error("启动实时处理失败", e);
            return R.fail("启动失败：" + e.getMessage());
        }
    }

    @ApiOperation("停止实时处理")
    @PostMapping("/realtime/stop")
    public R<Object> stopRealtimeProcess() {
        try {
            videoProcessService.stopRealtimeProcess();
            return R.ok("实时处理已停止");
        } catch (Exception e) {
            log.error("停止实时处理失败", e);
            return R.fail("停止失败：" + e.getMessage());
        }
    }

    @ApiOperation("下载处理结果")
    @GetMapping("/download/{taskId}")
    @Anonymous  // 添加此注解允许匿名访问
    public void downloadResult(@ApiParam(value = "任务ID", required = true) @PathVariable String taskId,
                               HttpServletResponse response) {
        try {
            File resultFile = videoProcessService.getResultFile(taskId);

            // 设置响应头
            response.setContentType("video/mp4");
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"processed_video.mp4\"");
            response.setHeader("Content-Length", String.valueOf(resultFile.length()));

            // 写入响应流
            try (FileInputStream fis = new FileInputStream(resultFile);
                 OutputStream os = response.getOutputStream()) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.flush();
            }

            log.info("下载处理结果成功，任务ID: {}", taskId);
        } catch (Exception e) {
            log.error("下载失败", e);
            try {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"code\":500,\"msg\":\"下载失败：" + e.getMessage() + "\"}");
            } catch (Exception ex) {
                log.error("写入错误响应失败", ex);
            }
        }
    }

    @ApiOperation("获取处理状态")
    @GetMapping("/status/{taskId}")
    public R<Object> getTaskStatus(@ApiParam(value = "任务ID", required = true) @PathVariable String taskId) {
        try {
            Double progress = videoProcessService.getProgress(taskId);

            String status;
            if (progress == null || progress == 0.0) {
                status = "PENDING";
            } else if (progress > 0 && progress < 100) {
                status = "PROCESSING";
            } else if (progress >= 100) {
                status = "COMPLETED";
            } else if (progress == -1.0) {
                status = "FAILED";
            } else {
                status = "UNKNOWN";
            }

            return R.ok(status);
        } catch (Exception e) {
            log.error("获取任务状态失败", e);
            return R.fail("获取状态失败：" + e.getMessage());
        }
    }

    public static class RealtimeParams {
        private String algorithm;
        private Double amplification;
        private Boolean temporalFilter;
        private Boolean otherSettings;
        private Double upperThreshold;
        private Double lowerThreshold;
        private Double spatialSigma;

        // getters and setters
        public String getAlgorithm() { return algorithm; }
        public void setAlgorithm(String algorithm) { this.algorithm = algorithm; }

        public Double getAmplification() { return amplification; }
        public void setAmplification(Double amplification) { this.amplification = amplification; }

        public Boolean getTemporalFilter() { return temporalFilter; }
        public void setTemporalFilter(Boolean temporalFilter) { this.temporalFilter = temporalFilter; }

        public Boolean getOtherSettings() { return otherSettings; }
        public void setOtherSettings(Boolean otherSettings) { this.otherSettings = otherSettings; }

        public Double getUpperThreshold() { return upperThreshold; }
        public void setUpperThreshold(Double upperThreshold) { this.upperThreshold = upperThreshold; }

        public Double getLowerThreshold() { return lowerThreshold; }
        public void setLowerThreshold(Double lowerThreshold) { this.lowerThreshold = lowerThreshold; }

        public Double getSpatialSigma() { return spatialSigma; }
        public void setSpatialSigma(Double spatialSigma) { this.spatialSigma = spatialSigma; }
    }
}