package com.ENSF480.airlineBackend.agent;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class AgentService {
    private final AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public boolean isValidAgent(String email, String password) {
        Agent agent = agentRepository.findAgentByEmail(email).orElseThrow(() -> new RuntimeException("Agent not found"));
        String hashedPassword = sha256(password);
        return agent.getPassword().equals(hashedPassword);
    }

    public void createAgent(Agent agentDetails){
        String hashedPassword = sha256(agentDetails.getPassword());
        Agent agent = new Agent(agentDetails.getName(), agentDetails.getEmail(), hashedPassword);
        agentRepository.save(agent);
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
