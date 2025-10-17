package spring.web.lesson.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "integrations.clients")
public class ProductClientProperties {

    private final RestTemplateProperties productClient;

    public ProductClientProperties(RestTemplateProperties productClient) {
        this.productClient = productClient;
    }

    public RestTemplateProperties getProductClient() {
        return productClient;
    }
}
