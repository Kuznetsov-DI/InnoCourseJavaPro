package spring.web.lesson.service;

import spring.web.lesson.dto.payment.PaymentRequest;
import spring.web.lesson.dto.payment.PaymentResponse;
import spring.web.lesson.dto.product.ProductDto;
import spring.web.lesson.exception.NoHaveProductsException;
import spring.web.lesson.exception.NotEnoughBalanceException;

import java.util.List;

public interface PaymentService {

    List<ProductDto> getProductBuUserId(Long userId);

    PaymentResponse pay(PaymentRequest request) throws NoHaveProductsException, NotEnoughBalanceException;
}
