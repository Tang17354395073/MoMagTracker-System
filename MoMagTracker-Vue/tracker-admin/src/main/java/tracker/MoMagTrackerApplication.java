package tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author tangjiawei
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class MoMagTrackerApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(MoMagTrackerApplication.class, args);
        System.out.println(
                "(♥◠‿◠)ﾉﾞ  MoMagTracker 项目启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "#####  #####  #   #\n" +
                "  #      #    #   #\n" +
                "  #      #    # # #\n" +
                "  #      #    ## ##\n" +
                "  #    ###    #   #\n" +
                "\n" +
                "📍 项目：MoMagTracker\n" +
                "👤 作者：唐佳伟       \n" +
                "🕒 时间：" + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +
                "\n" +
                "✨ 系统初始化完成，开始为您服务！");
    }
}
