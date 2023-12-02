package com.ENSF480.airlineBackend.staff;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class StaffService {
    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository){
        this.staffRepository = staffRepository;
    }

    public boolean isStaff(String email){
        Optional<Staff> staff = staffRepository.findStaffByEmail(email);
        return staff.isPresent();
    }

    public boolean isValidStaff(String email, String password) {
        Optional<Staff> staff = staffRepository.findStaffByEmail(email);
        if (!staff.isPresent()){
            return false;
        }
        Staff staffDetails = staff.get();
        String hashedPassword = sha256(password);
        return staffDetails.getPassword().equals(hashedPassword);
    }

    public Staff searchStaff(String email){
        return staffRepository.findStaffByEmail(email).get();
    }

    public void createStaff(Staff staffDetails){
        if (isStaff(staffDetails.getEmail())){
            throw new RuntimeException("Agent already exists");
        }
        String hashedPassword = sha256(staffDetails.getPassword());
        Staff agent = new Staff(staffDetails.getName(), staffDetails.getEmail(), hashedPassword);
        staffRepository.save(agent);
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
