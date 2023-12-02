package com.ENSF480.airlineBackend.admin;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public boolean isAdmin(String email){
        Optional<Admin> staff = adminRepository.findAdminByEmail(email);
        return staff.isPresent();
    }

    public Admin searchAdmin(String email){
        return adminRepository.findAdminByEmail(email).get();
    }

    public boolean isValidAdmin(String email, String password) {
        Optional<Admin> admin = adminRepository.findAdminByEmail(email);
        if (!admin.isPresent()){
            return false;
        }
        Admin adminDetails = admin.get();
        String hashedPassword = sha256(password);
        return adminDetails.getPassword().equals(hashedPassword);
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
