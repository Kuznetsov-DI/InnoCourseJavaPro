package spring.web.lesson.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.web.lesson.converter.ProductEntityToProductDto;
import spring.web.lesson.dto.ProductDto;
import spring.web.lesson.repository.ProductRepository;
import spring.web.lesson.service.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductEntityToProductDto converter;

    @Override
    @Transactional
    public List<ProductDto> getProductsByUserId(Long userId) {
        var productEntities = repository.findByUserId(userId);

        return productEntities.stream().map(converter::convert).toList();
    }

    @Override
    @Transactional
    public ProductDto getProductById(Long productId) {
        var productEntity = repository.getReferenceById(productId);

        return converter.convert(productEntity);
    }
}
