package com.library.apilibrary.service;

import com.library.apilibrary.model.dto.HistoryDto;

public interface HistoryService {
    public Object create(HistoryDto dto);

    public Object read();

    public Object update(HistoryDto dto, Long id);

    public Object getByUser(Long id);
}
