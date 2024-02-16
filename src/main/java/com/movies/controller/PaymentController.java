package com.movies.controller;

import com.movies.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/verify-amount")
    public boolean verifyAmount(@RequestParam int amount) {
        return paymentService.verifyAmount(amount);
    }

    @PostMapping("/send-otp")
    public boolean sendOTP(@RequestParam String email) {
        return paymentService.sendOTP(email);
    }

    @PostMapping("/verify-otp")
    public boolean verifyOTP(@RequestParam String email, @RequestParam String otp) {
        return paymentService.verifyOTP(email, otp);
    }
}