package com.library.apilibrary.service;

import com.library.apilibrary.model.dto.TypeDto;

public interface TypeService {
    public Object addType(TypeDto dto);

    public Object getAll();
}
