package com.financial.managerapi.controller;


import com.financial.managerapi.dto.AuthenticationResponse;
import com.financial.managerapi.dto.Login;
import com.financial.managerapi.dto.Register;
import com.financial.managerapi.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {


    private final UserService userService;

    /**
     * register function for create new user and token for access
     */
    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResponseEntity<AuthenticationResponse> register(
             @RequestBody @Valid Register request
    ){
        AuthenticationResponse response = userService.register(request);
        log.info("user is register {}",response.getUser().getFullName());
       return ResponseEntity.ok(response);
    }


    /**
     * login function for create token for user
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
             @RequestBody @Valid Login request
    ){
        AuthenticationResponse response = userService.login(request);
        log.info("user is logged in {}",response.getUser().getFullName());
        return ResponseEntity.ok(response);
    }







}
