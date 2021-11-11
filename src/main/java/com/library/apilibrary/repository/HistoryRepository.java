package com.library.apilibrary.repository;

import com.library.apilibrary.model.entity.History;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
    
}
