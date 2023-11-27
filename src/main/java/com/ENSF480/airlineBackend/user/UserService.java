package com.ENSF480.airlineBackend.user;

import org.springframework.stereotype.Service;

import com.ENSF480.airlineBackend.reg_user.RegisteredUser;
import com.ENSF480.airlineBackend.reg_user.RegisteredUserService;

@Service
public class UserService {
    
    private final RegisteredUserService registeredUserService;

    public UserService(RegisteredUserService registeredUserService){
        this.registeredUserService = registeredUserService;
    }

    public void GuestSignIn(User user){
        registeredUserService.existsAsRegistered(user);
    }

    public void GuestSignUp(RegisteredUser regUser){
        registeredUserService.existsAsRegistered(regUser);
        registeredUserService.createRegisteredUser(regUser);
    }
}
