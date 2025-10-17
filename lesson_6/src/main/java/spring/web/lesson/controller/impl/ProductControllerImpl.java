package spring.web.lesson.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.web.lesson.controller.ProductController;
import spring.web.lesson.dto.DebitRequestDto;
import spring.web.lesson.dto.ProductDto;
import spring.web.lesson.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Override
    @GetMapping("/user/{userId}")
    public List<ProductDto> getProductsByUserId(@PathVariable(name = "userId") Long userId) {

        return productService.getProductsByUserId(userId);
    }

    @Override
    @GetMapping("/{productId}")
    public ProductDto getProductById(@PathVariable(name = "productId") Long productId) {

        return productService.getProductById(productId);
    }

    @Override
    @PostMapping("/debit")
    public void debitByProductId(@RequestBody DebitRequestDto request) {
        productService.debitByProductId(request);
    }
}
