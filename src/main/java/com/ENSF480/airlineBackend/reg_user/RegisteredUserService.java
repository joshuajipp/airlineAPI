package com.ENSF480.airlineBackend.reg_user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ENSF480.airlineBackend.user.User;

@Service
public class RegisteredUserService {
    private final RegisteredUserRepository registeredUserRepository;

    public RegisteredUserService(RegisteredUserRepository registeredUserRepository){
        this.registeredUserRepository = registeredUserRepository;
    }

    // public void createRegisteredUser(User regUser){
    //     this.registeredUserRepository.save(regUser);
    // }

    public RegisteredUser regUserSignIn(RegisteredUser regUser){
        Optional<RegisteredUser> registeredUserOptional = registeredUserRepository.searchByEmail(regUser.getEmail());

        if (registeredUserOptional.isPresent()){
            if (registeredUserOptional.get().getPassword().equals(regUser.getPassword())){
                return registeredUserOptional.get();
            }
            else{
                throw new IllegalAccessError("Incorrect password");
            }
            
        }
        else {
            throw new IllegalStateException("User account does not exist. Sign up first");
        }
    }

    // public void existsAsRegistered(User user){
    //     Optional<RegisteredUser> registeredUserOptional = registeredUserRepository.searchByEmail(user.getEmail());

    //     if (registeredUserOptional.isPresent()){
    //         throw new IllegalStateException("Registered User with provided email already exists");
    //     }
    //     return;
    // }
}
