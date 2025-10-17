package spring.web.lesson.dto.payment;

public record PaymentRequest(Long userId, String accountNumber, Double sum) {
}
