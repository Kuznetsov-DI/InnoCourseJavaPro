package spring.web.lesson.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductDto {
    private final Long id;
    private final String accountNumber;
    private final String balance;
    private final String productType;
    private final Long userId;
}
