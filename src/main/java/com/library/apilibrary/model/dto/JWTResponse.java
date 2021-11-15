package com.library.apilibrary.model.dto;

import java.util.Set;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JWTResponse {
    private String token;
    private String email;
    private Object username;
    private Set<String> role;
    private String type = "Bearer";


    public JWTResponse(String token, String email, Object username, Set<String> role) {
        this.token = token;
        this.email = email;
        this.username = username;
        this.role = role;
    }



}
