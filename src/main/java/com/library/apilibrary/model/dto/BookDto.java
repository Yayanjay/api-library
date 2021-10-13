package com.library.apilibrary.model.dto;

import com.library.apilibrary.model.entity.Genre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private String bookName;
    private String bookAuthor;
    private String bookimage;
    private String bookDesc;
    private Genre genreId;

}

