package com.ENSF480.airlineBackend.admin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Admin {
    @Id
    @SequenceGenerator(
        name = "agent_sequence",
        sequenceName = "agent_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "agent_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private String password;

    public Admin() {
    }

    public Admin(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public void setPassword(String password){
        this.password=password;
    }

    
}
