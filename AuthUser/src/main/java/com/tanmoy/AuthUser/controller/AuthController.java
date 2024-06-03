package com.tanmoy.AuthUser.controller;

import com.tanmoy.AuthUser.request.AuthRequest;
import com.tanmoy.AuthUser.request.UserRequest;
import com.tanmoy.AuthUser.response.BaseResponse;
import com.tanmoy.AuthUser.security.JwtService;
import com.tanmoy.AuthUser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping("/user-registration")
    public ResponseEntity<BaseResponse> userRegistration(@RequestBody UserRequest request) {
        return ResponseEntity.ok(BaseResponse.getSuccessResponse(userService.saveUser(request)));
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(BaseResponse.getSuccessResponse(jwtService.generateToken(authRequest.getUsername(), authentication)));
        } else {
            throw new UsernameNotFoundException("invalid user request!");
        }
    }

}
