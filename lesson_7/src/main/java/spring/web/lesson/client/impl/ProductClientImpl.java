package spring.web.lesson.client.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import spring.web.lesson.client.ProductClient;
import spring.web.lesson.dto.product.DebitRequestDto;
import spring.web.lesson.dto.product.ProductDto;

import java.util.List;

@Component
public class ProductClientImpl implements ProductClient {

    private static final Logger log = LoggerFactory.getLogger(ProductClientImpl.class);

    private final RestTemplate restTemplateProductClient;

    public ProductClientImpl(RestTemplate restTemplateProductClient) {
        this.restTemplateProductClient = restTemplateProductClient;
    }

    @Override
    public ProductDto getProductById(Long productId) {
        log.info("Getting product by productId - {}", productId);
        return restTemplateProductClient.getForObject(
                String.format("/%s", productId),
                ProductDto.class);
    }

    @Override
    public List<ProductDto> getProductsByUserId(Long userId) {
        log.info("Getting products by userId - {}", userId);
        var response = restTemplateProductClient.exchange(
                String.format("/user/%s", userId),
                HttpMethod.GET,
                new HttpEntity<>(null),
                new ParameterizedTypeReference<List<ProductDto>>() {
                }
        );

        return response.getBody();
    }

    @Override
    public void debitByProductId(DebitRequestDto request) {
        log.info("Debit from balance");
        restTemplateProductClient.postForEntity(
                "/debit",
                request,
                Object.class
        );
    }
}
