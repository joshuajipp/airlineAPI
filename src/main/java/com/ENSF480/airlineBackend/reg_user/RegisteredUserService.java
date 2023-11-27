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

    public void createRegisteredUser(User regUser){
        this.registeredUserRepository.save(regUser);
    }

    public void existsAsRegistered(User user){
        Optional<RegisteredUser> registeredUserOptional = registeredUserRepository.searchByEmail(user.getEmail());

        if (registeredUserOptional.isPresent()){
            throw new IllegalStateException("Registered User with provided email already exists");
        }
        return;
    }
}
