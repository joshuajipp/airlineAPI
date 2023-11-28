package com.ENSF480.airlineBackend.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ENSF480.airlineBackend.reg_user.RegisteredUser;
import com.ENSF480.airlineBackend.reg_user.RegisteredUserService;

@RestController
@RequestMapping(path="api/v1/regUser")
public class UserController {
    
    private RegisteredUserService registeredUserService;

    public UserController(RegisteredUserService registeredUserService){
        this.registeredUserService = registeredUserService;
    }

    @PostMapping
    public void createRegisteredUser(@RequestBody RegisteredUser newUser){
        registeredUserService.createRegisteredUser(newUser);
    }
}
