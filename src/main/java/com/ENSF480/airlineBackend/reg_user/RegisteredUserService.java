package com.ENSF480.airlineBackend.reg_user;

import java.util.Optional;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Service;

@Service
public class RegisteredUserService {
    private final RegisteredUserRepository registeredUserRepository;

    public RegisteredUserService(RegisteredUserRepository registeredUserRepository){
        this.registeredUserRepository = registeredUserRepository;
    }

    public RegisteredUser isValidRegisteredUser(String email, String password){
        if (registeredUserExists(email)){
            String hashedPassword = sha256(password);
            Optional<RegisteredUser> user = registeredUserRepository.findRegisteredUserByEmail(email);
            if (user.get().getPassword().equals(hashedPassword)){
                return user.get();
            }
            else{
                throw new RuntimeException("Incorrect Password");
            }
        }
        else{
            throw new RuntimeException("Registered User does not exist");
        }
    }

    public boolean registeredUserExists(String email){
        Optional<RegisteredUser> user = registeredUserRepository.findRegisteredUserByEmail(email);

        if (user.isPresent()){
            return true;
        }

        else{
            return false;
        }
    }

    public void createRegisteredUser(RegisteredUser newUser){
        
        if (!registeredUserExists(newUser.getEmail())){
            String hashedPassword = sha256(newUser.getPassword());
            newUser.setPassword(hashedPassword);
            registeredUserRepository.save(newUser);
    
        }
        else {
            throw new RuntimeException("Registered user with that email already exists");
        }
    }

    public static String toHexString(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private static String sha256(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            return toHexString(hash);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    
}
