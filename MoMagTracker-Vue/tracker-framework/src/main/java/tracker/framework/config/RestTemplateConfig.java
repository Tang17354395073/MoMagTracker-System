package tracker.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        // 设置连接超时时间为30秒（30000毫秒）
        factory.setConnectTimeout(30000);
        // 设置读取超时时间为30秒（30000毫秒）
        factory.setReadTimeout(30000);
        return new RestTemplate(factory);
    }
}