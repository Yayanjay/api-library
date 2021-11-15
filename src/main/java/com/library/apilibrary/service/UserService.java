package com.library.apilibrary.service;

import com.library.apilibrary.model.dto.UserDto;

public interface UserService {
    public Object create(UserDto dto);

    public Object read();

    public Object update(UserDto dto, Long id);

    public Object delete(Long id);

    public Object getByEmail(String email);

}
