package com.library.apilibrary.model.dto;

import java.util.Set;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JWTResponse {
    private String token;
    private String email;
    private Long id;
    private String username;
    private Set<String> role;
    private String type = "Bearer";


    public JWTResponse(String token, String email, Long id, String username, Set<String> role) {
        this.token = token;
        this.email = email;
        this.id = id;
        this.username = username;
        this.role = role;
    }



}
