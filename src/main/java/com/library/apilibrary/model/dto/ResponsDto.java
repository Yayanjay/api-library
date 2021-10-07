package com.library.apilibrary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsDto<T> {
    private int status;
    private Object description;
    private String message;
    private T data;
}
