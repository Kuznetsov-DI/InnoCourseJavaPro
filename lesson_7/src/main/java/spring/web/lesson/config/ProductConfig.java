package spring.web.lesson.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import spring.web.lesson.client.handlers.ProductClientResponseHandler;
import spring.web.lesson.config.properties.ProductClientProperties;

@Configuration
@EnableConfigurationProperties({ProductClientProperties.class})
public class ProductConfig {

    private final ProductClientProperties productClientProperties;

    public ProductConfig(ProductClientProperties productClientProperties) {
        this.productClientProperties = productClientProperties;
    }

    @Bean
    public RestTemplate restTemplateProductClient(ProductClientResponseHandler productClientResponseHandler) {
        var executorClient = productClientProperties.getProductClient();

        return new RestTemplateBuilder()
                .rootUri(executorClient.getUrl())
                .setConnectTimeout(executorClient.getConnectTimeout())
                .setReadTimeout(executorClient.getReadTimeout())
                .errorHandler(productClientResponseHandler)
                .build();
    }
}
