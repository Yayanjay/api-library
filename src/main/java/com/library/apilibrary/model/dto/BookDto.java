package com.library.apilibrary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private String bookName;
    private String bookAuthor;
    private String bookImage;
    private String bookDesc;
    private Long genreId;
    private Long typeId;

}

