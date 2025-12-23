package tracker.system.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;

public interface IVideoProcessService {

    /**
     * 处理视频文件
     */
    String processVideo(MultipartFile videoFile,
                        String algorithm,
                        Double amplification,
                        Double upperThreshold,
                        Double lowerThreshold) throws Exception;

    /**
     * 获取处理进度
     */
    Double getProgress(String taskId);

    /**
     * 开始实时处理
     */
    void startRealtimeProcess(String algorithm,
                              Double amplification,
                              Double upperThreshold,
                              Double lowerThreshold) throws Exception;

    /**
     * 停止实时处理
     */
    void stopRealtimeProcess();

    /**
     * 获取处理结果文件
     */
    File getResultFile(String taskId) throws Exception;
}