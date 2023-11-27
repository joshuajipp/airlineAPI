package com.ENSF480.airlineBackend.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ENSF480.airlineBackend.reg_user.RegisteredUser;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    // @GetMapping
    // public User GuestSignIn(@RequestBody User user){
    //     userService.GuestSignIn(user);
    //     return user;
    // }

    // @PostMapping
    // public void GuestSignUp(@RequestBody RegisteredUser regUser){
    //     userService.GuestSignUp(regUser);
    // }
}
