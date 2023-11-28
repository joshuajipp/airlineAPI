package com.ENSF480.airlineBackend.reg_user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/login")
public class RegisteredUserController {
    private final RegisteredUserService registeredUserService;

    public RegisteredUserController(RegisteredUserService registeredUserService){
        this.registeredUserService = registeredUserService;
    }

    @PostMapping
    public RegisteredUser isValidRegisteredUser(@RequestBody RegisteredUser loginUser){
        RegisteredUser userInfo = registeredUserService.isValidRegisteredUser(loginUser.getEmail(), loginUser.getPassword());
        return userInfo;
    }
}
