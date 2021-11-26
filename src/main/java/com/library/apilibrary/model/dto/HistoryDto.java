package com.library.apilibrary.model.dto;

import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDto {
    private String date = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss").toString();
    private String librarian = "Zayyan";
    private Long bookId;
    private Long userId;
    private String userName;
    private String status;
}
