package com.library.apilibrary.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    
    @Column(length = 20, unique = true, nullable = false)
    private String userName;

    @Column(length = 100, unique = true, nullable = false)
    private String userEmail;
    
    @Column(nullable = false)
    private String userPassword;

    @Column(length = 10, nullable = false)
    private String userRole;

    @Column(nullable = false)
    private boolean isDeleted = false;

    public User(String userName, String userEmail, String userPassword, String userRole) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userRole = userRole;
    }

}
