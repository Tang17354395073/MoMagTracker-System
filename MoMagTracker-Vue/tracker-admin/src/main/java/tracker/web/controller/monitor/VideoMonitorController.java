package tracker.web.controller.monitor;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tracker.common.core.controller.BaseController;
import tracker.common.core.domain.AjaxResult;
import tracker.common.core.domain.R;
import tracker.system.service.IVideoProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;

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
            @RequestParam("video") MultipartFile videoFile,
            @RequestParam("algorithm") String algorithm,
            @RequestParam("amplification") Double amplification,
            @RequestParam("upperThreshold") Double upperThreshold,
            @RequestParam("lowerThreshold") Double lowerThreshold) {

        try {
            String taskId = videoProcessService.processVideo(
                    videoFile,
                    algorithm,
                    amplification,
                    upperThreshold,
                    lowerThreshold
            );
            return R.ok(taskId);
        } catch (Exception e) {
            log.error("视频处理失败", e);
            return R.fail("视频处理失败：" + e.getMessage());
        }
    }

    @ApiOperation("获取处理进度")
    @GetMapping("/progress/{taskId}")
    public R<Object> getProgress(@PathVariable String taskId) {
        try {
            Double progress = videoProcessService.getProgress(taskId);
            return R.ok(progress);
        } catch (Exception e) {
            return R.fail("获取进度失败");
        }
    }

    @ApiOperation("开始实时处理")
    @PostMapping("/realtime/start")
    public R<Object> startRealtimeProcess(@RequestBody RealtimeParams params) {
        try {
            videoProcessService.startRealtimeProcess(
                    params.getAlgorithm(),
                    params.getAmplification(),
                    params.getUpperThreshold(),
                    params.getLowerThreshold()
            );
            return R.ok("实时处理已启动");
        } catch (Exception e) {
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
            return R.fail("停止失败：" + e.getMessage());
        }
    }

    @ApiOperation("下载处理结果")
    @GetMapping("/download/{taskId}")
    public void downloadResult(@PathVariable String taskId, HttpServletResponse response) {
        try {
            File resultFile = videoProcessService.getResultFile(taskId);
            response.setContentType("video/mp4");
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"processed_video.mp4\"");
            Files.copy(resultFile.toPath(), response.getOutputStream());
        } catch (Exception e) {
            log.error("下载失败", e);
        }
    }

    public static class RealtimeParams {
        private String algorithm;
        private Double amplification;
        private Double upperThreshold;
        private Double lowerThreshold;

        // getters and setters
        public String getAlgorithm() { return algorithm; }
        public void setAlgorithm(String algorithm) { this.algorithm = algorithm; }
        public Double getAmplification() { return amplification; }
        public void setAmplification(Double amplification) { this.amplification = amplification; }
        public Double getUpperThreshold() { return upperThreshold; }
        public void setUpperThreshold(Double upperThreshold) { this.upperThreshold = upperThreshold; }
        public Double getLowerThreshold() { return lowerThreshold; }
        public void setLowerThreshold(Double lowerThreshold) { this.lowerThreshold = lowerThreshold; }
    }
}