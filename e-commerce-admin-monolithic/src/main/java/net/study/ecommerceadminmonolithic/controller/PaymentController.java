package net.study.ecommerceadminmonolithic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    @GetMapping("daily-cnt")
    public int getPaymentCount() {
        // TODO: 일간 결제 서비스 기능 추가
        return 3;
    }

    @GetMapping("daily-cancel-cnt")
    public int getPaymentCancelCount() {
        // TODO: 일간 결제 취소 서비스 기능 추가
        return 2;
    }
}
