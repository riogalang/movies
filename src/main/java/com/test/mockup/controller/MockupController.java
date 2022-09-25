package com.test.mockup.controller;

import com.test.mockup.dto.AuthenticationRequestDto;
import com.test.mockup.dto.BiodataDto;
import com.test.mockup.dto.FillingFormRequest;
import com.test.mockup.dto.LoginResponse;
import com.test.mockup.entity.UserAuthentication;
import com.test.mockup.service.MockupService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockupController {

    private final MockupService mockupService;

    public MockupController(MockupService mockupService) {
        this.mockupService = mockupService;
    }

    @PostMapping("/update")
    public BiodataDto update(@RequestBody FillingFormRequest fillingFormRequest){
        return mockupService.update(fillingFormRequest);
    }

    @PostMapping("/signup")
    public UserAuthentication signUp(@RequestBody AuthenticationRequestDto authenticationRequestDto){
        return mockupService.signUp(authenticationRequestDto);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody AuthenticationRequestDto authenticationRequestDto){
        return mockupService.login(authenticationRequestDto);
    }

    @PostMapping("/filling-form")
    public BiodataDto fillingForm(@RequestBody FillingFormRequest fillingFormRequest){
        return mockupService.fillingForm(fillingFormRequest);
    }

    @PostMapping("/delete")
    public String delete(@RequestBody FillingFormRequest fillingFormRequest){
        return mockupService.delete(fillingFormRequest);
    }


}
