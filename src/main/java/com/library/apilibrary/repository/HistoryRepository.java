package com.library.apilibrary.repository;

import java.util.List;

import com.library.apilibrary.model.entity.History;
import com.library.apilibrary.model.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;


public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByUserId(User userId);
    
}
