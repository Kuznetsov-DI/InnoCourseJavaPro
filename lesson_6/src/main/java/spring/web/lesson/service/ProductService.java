package spring.web.lesson.service;

import spring.web.lesson.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getProductsByUserId(Long userId);

    ProductDto getProductById(Long productId);
}
