package com.carData.service;

import com.carData.model.AuthenticationResponse;
import com.carData.model.UserInfo;
import com.carData.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserInfo request) {

        UserInfo user = new UserInfo();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        repo.save(user);

        String accessToken = jwtService.generateAccessToken(user);

        return new AuthenticationResponse(accessToken);
    }

    public AuthenticationResponse login(UserInfo loginRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        String accessToken = jwtService.generateAccessToken(loginRequest);
        return new AuthenticationResponse(accessToken);
    }
}
