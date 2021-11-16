package com.library.apilibrary.repository;

import com.library.apilibrary.model.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    User findByUserEmail(String userEmail);
    User findByUserRole(String userRole);
}
