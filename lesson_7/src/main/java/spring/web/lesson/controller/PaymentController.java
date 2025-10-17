package spring.web.lesson.controller;

import spring.web.lesson.dto.payment.PaymentRequest;
import spring.web.lesson.dto.payment.PaymentResponse;
import spring.web.lesson.dto.product.ProductDto;
import spring.web.lesson.exception.NoHaveProductsException;
import spring.web.lesson.exception.NotEnoughBalanceException;

import java.util.List;

public interface PaymentController {

    List<ProductDto> getProductsByUserId(Long userId);

    PaymentResponse pay(PaymentRequest request) throws NoHaveProductsException, NotEnoughBalanceException;
}
