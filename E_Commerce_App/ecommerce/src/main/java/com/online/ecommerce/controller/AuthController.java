package com.online.ecommerce.controller;

import com.online.ecommerce.model.UserInfo;
import com.online.ecommerce.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth/product")
public class AuthController {

    @Autowired
    private UserInfoService userInfoService;

    // Customer registration
    @PostMapping("/customer/register")
    public String customerRegistration(@RequestBody UserInfo userInfo){
        return userInfoService.customerRegistration(userInfo);
    }

    // Customer login
    @PostMapping("/customer/login")
    public String customerLogin(@RequestBody UserInfo userInfo){
        return userInfoService.customerLogin(userInfo);
    }


}
