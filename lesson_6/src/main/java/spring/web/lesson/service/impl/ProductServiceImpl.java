package spring.web.lesson.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import spring.web.lesson.converter.ProductEntityToProductDto;
import spring.web.lesson.dto.DebitRequestDto;
import spring.web.lesson.dto.ProductDto;
import spring.web.lesson.exception.NotEnoughBalanceException;
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

    @Override
    @SneakyThrows
    @Transactional
    public void debitByProductId(DebitRequestDto request) {
        var productEntity = repository.getReferenceById(request.productId());
        if (request.sum() > productEntity.getBalance()) {
            throw new NotEnoughBalanceException("Not enough balance for debit for product - " + productEntity.getId());
        }
        var newBalance = productEntity.getBalance() - request.sum();

        repository.updateBalance(productEntity.getId(), newBalance);
    }
}
