package spring.web.lesson.converter;

import org.springframework.stereotype.Component;
import spring.web.lesson.dto.ProductDto;
import spring.web.lesson.entity.Product;

@Component
public class ProductEntityToProductDto {

    public ProductDto convert(Product entity) {
        return new ProductDto(entity.getId(), entity.getAccountNumber(), entity.getBalance(),
                entity.getProductType().getValue(), entity.getUser().getId());
    }
}
