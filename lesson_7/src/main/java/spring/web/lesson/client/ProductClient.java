package spring.web.lesson.client;

import spring.web.lesson.dto.product.DebitRequestDto;
import spring.web.lesson.dto.product.ProductDto;

import java.util.List;

public interface ProductClient {

    ProductDto getProductById(Long productId);

    List<ProductDto> getProductsByUserId(Long userId);

    void debitByProductId(DebitRequestDto request);
}
