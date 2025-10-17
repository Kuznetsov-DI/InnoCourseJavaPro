package spring.web.lesson.controller.impl;

import org.springframework.web.bind.annotation.*;
import spring.web.lesson.controller.PaymentController;
import spring.web.lesson.dto.payment.PaymentRequest;
import spring.web.lesson.dto.payment.PaymentResponse;
import spring.web.lesson.dto.product.ProductDto;
import spring.web.lesson.exception.NoHaveProductsException;
import spring.web.lesson.exception.NotEnoughBalanceException;
import spring.web.lesson.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentControllerImpl implements PaymentController {

    private final PaymentService paymentService;

    public PaymentControllerImpl(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    @GetMapping("/products/{userId}")
    public List<ProductDto> getProductsByUserId(@PathVariable Long userId) {
        return paymentService.getProductBuUserId(userId);
    }

    @Override
    @PostMapping("/pay")
    public PaymentResponse pay(@RequestBody PaymentRequest request) throws NoHaveProductsException, NotEnoughBalanceException {
        return paymentService.pay(request);
    }
}
