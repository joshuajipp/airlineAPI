package com.ENSF480.airlineBackend.reg_user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ENSF480.airlineBackend.agent.AgentService;

@RestController
@RequestMapping(path = "api/v1/login")
public class RegisteredUserController {
    private final RegisteredUserService registeredUserService;
    private final AgentService agentService;

    public RegisteredUserController(RegisteredUserService registeredUserService, AgentService agentService){
        this.registeredUserService = registeredUserService;
        this.agentService = agentService;
    }

    @PostMapping
    public Response isValidRegisteredUser(@RequestBody RegisteredUser loginUser){
        if(this.agentService.isAgent(loginUser.getEmail())){
            if (this.agentService.isValidAgent(loginUser.getEmail(), loginUser.getPassword())){
                return new Response(this.agentService.searchAgent(loginUser.getEmail()), "Agent");
            }
            else{
                throw new RuntimeException("Incorrect password");
            }
            
        }
        else{
            RegisteredUser userInfo = this.registeredUserService.isValidRegisteredUser(loginUser.getEmail(), loginUser.getPassword());
            return new Response(userInfo, "Registered User");
        }
    }
}