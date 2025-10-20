package spring.web.lesson.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import spring.web.lesson.client.ProductClient;
import spring.web.lesson.dto.payment.PaymentRequest;
import spring.web.lesson.dto.payment.PaymentResponse;
import spring.web.lesson.dto.product.DebitRequestDto;
import spring.web.lesson.dto.product.ProductDto;
import spring.web.lesson.exception.NoHaveProductsException;
import spring.web.lesson.exception.NotEnoughBalanceException;
import spring.web.lesson.service.PaymentService;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private final ProductClient productClient;

    public PaymentServiceImpl(ProductClient productClient) {
        this.productClient = productClient;
    }

    @Override
    public List<ProductDto> getProductBuUserId(Long userId) {
        return productClient.getProductsByUserId(userId);
    }

    @Override
    public PaymentResponse pay(PaymentRequest request) throws NoHaveProductsException, NotEnoughBalanceException {
        log.info("Payment starting");
        var products = productClient.getProductsByUserId(request.userId());

        var productForPayment = products.stream()
                .filter(el -> request.accountNumber().equals(el.accountNumber()))
                .findFirst()
                .orElseThrow(() -> new NoHaveProductsException("Account with number - " + request.accountNumber() + " doesn't exist for user - " + request.userId()));

        if (productForPayment.balance() < request.sum()) {
            throw new NotEnoughBalanceException("You have not enough balance for pay in account - " + request.accountNumber());
        }

        productClient.debitByProductId(new DebitRequestDto(productForPayment.id(), request.sum()));

        return new PaymentResponse(true);
    }
}
