package com.ENSF480.airlineBackend.reg_user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ENSF480.airlineBackend.admin.AdminService;
import com.ENSF480.airlineBackend.agent.AgentService;
import com.ENSF480.airlineBackend.staff.StaffService;

@RestController
@RequestMapping(path = "api/v1/login")
public class RegisteredUserController {
    private final RegisteredUserService registeredUserService;
    private final AgentService agentService;
    private final StaffService staffService;
    private final AdminService adminService;

    public RegisteredUserController(RegisteredUserService registeredUserService, AgentService agentService, StaffService staffService, AdminService adminService){
        this.registeredUserService = registeredUserService;
        this.agentService = agentService;
        this.staffService = staffService;
        this.adminService = adminService;
    }

    @PostMapping
    public Response isValidRegisteredUser(@RequestBody RegisteredUser loginUser){

        if (this.adminService.isAdmin(loginUser.getEmail())) {
            if (this.adminService.isValidAdmin(loginUser.getEmail(), loginUser.getPassword())){
                return new Response(this.adminService.searchAdmin(loginUser.getEmail()), "Admin");
            }
            else {
                throw new RuntimeException("Incorrect password");
            }
        }

        else if(this.agentService.isAgent(loginUser.getEmail())){
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