package com.online.ecommerce.service;

import com.online.ecommerce.model.UserInfo;
import com.online.ecommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String customerRegistration(UserInfo userInfo){
        UserInfo user = new UserInfo();
        user.setUsername(userInfo.getUsername());
        user.setRole(userInfo.getRole());
        user.setPassword(passwordEncoder.encode(userInfo.getPassword()));

        userRepo.save(user);

        String accessToken = jwtService.generateToken(userInfo);

        return accessToken;
    }

    public String customerLogin(UserInfo userInfo){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userInfo.getUsername(),
                        userInfo.getPassword()
                )
        );
        String accessToken = jwtService.generateToken(userInfo);
        return accessToken;
    }
}
