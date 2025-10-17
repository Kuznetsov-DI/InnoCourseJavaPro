package spring.web.lesson.dto.error;

import java.time.LocalDateTime;

public record PaymentErrorResponse(LocalDateTime dateTime, String message) {
}
