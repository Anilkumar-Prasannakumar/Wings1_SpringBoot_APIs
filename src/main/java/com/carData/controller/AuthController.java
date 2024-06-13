package com.carData.controller;

import com.carData.model.AuthenticationResponse;
import com.carData.model.UserInfo;
import com.carData.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserInfo userInfo){
        return ResponseEntity.ok(authService.register(userInfo));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserInfo userInfo){
        return ResponseEntity.ok(authService.login(userInfo));
    }
}
