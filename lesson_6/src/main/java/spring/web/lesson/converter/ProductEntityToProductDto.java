package spring.web.lesson.converter;

import lombok.experimental.UtilityClass;
import spring.web.lesson.dto.ProductDto;
import spring.web.lesson.entity.Product;

@UtilityClass
public class ProductEntityToProductDto {

    public ProductDto convert(Product entity) {
        return ProductDto.builder()
                .id(entity.getId())
                .accountNumber(entity.getAccountNumber())
                .balance(entity.getBalance())
                .productType(entity.getProductType().getValue())
                .userId(entity.getUser().getId())
                .build();
    }
}
