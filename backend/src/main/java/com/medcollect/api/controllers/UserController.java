package com.medcollect.api.controllers;

import com.medcollect.api.dtos.CreateUserRequest;
import com.medcollect.api.models.User;
import com.medcollect.api.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Valid CreateUserRequest request) {
        return userService.createUser(request);
    }
}
