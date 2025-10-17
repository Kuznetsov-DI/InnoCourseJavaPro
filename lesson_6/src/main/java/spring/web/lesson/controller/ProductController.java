package spring.web.lesson.controller;

import spring.web.lesson.dto.DebitRequestDto;
import spring.web.lesson.dto.ProductDto;

import java.util.List;

public interface ProductController {

    List<ProductDto> getProductsByUserId(Long userId);

    ProductDto getProductById(Long productId);

    void debitByProductId(DebitRequestDto request);
}
