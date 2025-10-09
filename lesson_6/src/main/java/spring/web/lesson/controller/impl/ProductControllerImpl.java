package spring.web.lesson.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import spring.web.lesson.controller.ProductController;
import spring.web.lesson.dto.ProductDto;
import spring.web.lesson.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Override
    @GetMapping("/products/{userId}")
    public List<ProductDto> getProductsByUserId(@PathVariable(name = "userId")Long userId) {

        return productService.getProductsByUserId(userId);
    }

    @Override
    @GetMapping("/product/{productId}")
    public ProductDto getProductById(@PathVariable(name = "productId") Long productId) {

        return productService.getProductById(productId);
    }
}
