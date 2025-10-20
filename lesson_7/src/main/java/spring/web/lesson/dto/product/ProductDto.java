package spring.web.lesson.dto.product;

public record ProductDto(Long id, String accountNumber, Double balance, String productType, Long userId) {
}
