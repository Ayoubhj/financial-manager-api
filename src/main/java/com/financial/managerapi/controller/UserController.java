package com.financial.managerapi.controller;

import com.financial.managerapi.dto.ImageBase64;
import com.financial.managerapi.dto.PasswordChange;
import com.financial.managerapi.dto.UserDto;
import com.financial.managerapi.entities.User;
import com.financial.managerapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/update-image/{id}")
    public ResponseEntity<User> updateImage(@RequestBody ImageBase64 image, @PathVariable Long id)  {
        return ResponseEntity.ok(userService.updateImage(image,id));
    }

    @PostMapping("/update-user")
    public ResponseEntity<User> updateUser(@RequestBody UserDto user)  {
        
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @PostMapping("/update-password/{id}")
    public ResponseEntity<User> updatePassword(@RequestBody PasswordChange change, @PathVariable Long id)  {

        return ResponseEntity.ok(userService.updatePassword(change,id));
    }
}
