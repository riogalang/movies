package com.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final EmailService emailService;
    private final Map<String, String> emailToOtpMap;

    @Autowired
    public PaymentServiceImpl(EmailService emailService) {
        this.emailService = emailService;
        this.emailToOtpMap = new HashMap<>();
    }

    @Override
    public boolean verifyAmount(int amount) {
        // Logic to verify the displayed amount
        return amount > 0 && amount <= 1000; // Example logic: Amount should be positive and less than or equal to 1000
    }

    @Override
    public boolean sendOTP(String email) {
        // Generate a random 6-digit OTP
        String otp = generateOTP();

        // Send the OTP to the provided email
        boolean emailSent = emailService.sendEmail(email, "OTP for Payment", "Your OTP is: " + otp);

        // If email sent successfully, store the OTP in a map with the email as the key
        if (emailSent) {
            emailToOtpMap.put(email, otp);
        }

        return emailSent;
    }

    @Override
    public boolean verifyOTP(String email, String otp) {
        // Retrieve the OTP associated with the provided email
        String storedOtp = emailToOtpMap.get(email);

        // Check if the provided OTP matches the stored OTP
        return storedOtp != null && storedOtp.equals(otp);
    }

    private String generateOTP() {
        // Generate a random 6-digit OTP
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000); // Random number between 100000 and 999999
        return String.valueOf(otpValue);
    }
}
