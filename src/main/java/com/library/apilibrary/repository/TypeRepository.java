package com.library.apilibrary.repository;

import com.library.apilibrary.model.entity.Type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends  JpaRepository<Type, Long>{
    
}
