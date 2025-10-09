package spring.web.lesson.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductType {
    ACCOUNT("счет"),
    CARD("карта");

    private final String value;
}
