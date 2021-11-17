package com.library.apilibrary.service;

import javax.transaction.Transactional;

import com.library.apilibrary.model.dto.ResponsDto;
import com.library.apilibrary.model.dto.TypeDto;
import com.library.apilibrary.model.entity.Type;
import com.library.apilibrary.repository.TypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {

    ResponsDto<Object> response = new ResponsDto<>();

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public Object addType(TypeDto dto) {
        // TODO Auto-generated method stub
        Type type = new Type(dto.getType());
        typeRepository.save(type);

        response.setStatus(HttpStatus.OK.value());
        response.setDescription(HttpStatus.OK);
        response.setMessage("Success");
        response.setResult(typeRepository.findAll());

        return response;
    }

    @Override
    public Object getAll() {
        // TODO Auto-generated method stub
        ResponsDto<Object> response = new ResponsDto<>();
        
        response.setStatus(HttpStatus.OK.value());
        response.setDescription(HttpStatus.OK);
        response.setMessage("Success");
        response.setResult(typeRepository.findAll());

        return response;
    }
    
}
