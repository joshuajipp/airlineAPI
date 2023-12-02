package com.ENSF480.airlineBackend.reg_user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ENSF480.airlineBackend.agent.AgentService;
import com.ENSF480.airlineBackend.staff.StaffService;

@RestController
@RequestMapping(path = "api/v1/login")
public class RegisteredUserController {
    private final RegisteredUserService registeredUserService;
    private final AgentService agentService;
    private final StaffService staffService;

    public RegisteredUserController(RegisteredUserService registeredUserService, AgentService agentService, StaffService staffService){
        this.registeredUserService = registeredUserService;
        this.agentService = agentService;
        this.staffService = staffService;
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
        else if (this.staffService.isStaff(loginUser.getEmail())){
            if (this.staffService.isValidStaff(loginUser.getEmail(), loginUser.getPassword())){
                return new Response(this.staffService.searchStaff(loginUser.getEmail()), "Staff");
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