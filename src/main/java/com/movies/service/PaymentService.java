package com.movies.service;

public interface PaymentService {
    boolean verifyAmount(int amount);
    boolean sendOTP(String email);
    boolean verifyOTP(String email, String otp);
}
