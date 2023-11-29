package com.ENSF480.airlineBackend.agent;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class AgentService {
    private final AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public boolean isAgent(String email) {
        Optional<Agent> agent = agentRepository.findAgentByEmail(email);
        return agent.isPresent();
    }

    public boolean isValidAgent(String email, String password) {
        Optional<Agent> agent = agentRepository.findAgentByEmail(email);
        if (!agent.isPresent()){
            return false;
        }
        Agent agentDetails = agent.get();
        String hashedPassword = sha256(password);
        return agentDetails.getPassword().equals(hashedPassword);
    }

    public Agent searchAgent(String email){
        return agentRepository.findAgentByEmail(email).get();
    }

    public void createAgent(Agent agentDetails){
        if (isAgent(agentDetails.getEmail())){
            throw new RuntimeException("Agent already exists");
        }
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
