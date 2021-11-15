package com.library.apilibrary.model.dto;

import java.util.Set;

import com.library.apilibrary.model.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JWTResponse {
    private String token;
    private String email;
    private String username;
    private Set<String> role;
    private String type = "Bearer";


    public JWTResponse(String token, String email, String username, Set<String> role) {
        this.token = token;
        this.email = email;
        this.username = username;
        this.role = role;
    }



}
