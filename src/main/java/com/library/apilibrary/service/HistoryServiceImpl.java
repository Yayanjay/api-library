package com.library.apilibrary.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.library.apilibrary.model.dto.HistoryDto;
import com.library.apilibrary.model.dto.ResponsDto;
import com.library.apilibrary.model.entity.Book;
import com.library.apilibrary.model.entity.History;
import com.library.apilibrary.model.entity.User;
import com.library.apilibrary.repository.BookRepository;
import com.library.apilibrary.repository.HistoryRepository;
import com.library.apilibrary.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Object create(HistoryDto dto) {
        // TODO Auto-generated method stub
        ResponsDto<Object> response = new ResponsDto<>();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
        LocalDateTime datetime = LocalDateTime.now();
        String date = datetime.format(formatter);

        User user = userRepository.findById(dto.getUserId()).get();
        Book book = bookRepository.findById(dto.getBookId()).get();
        History history = new History(dto.getLibrarian(), date, dto.getStatus(), user, book);
        historyRepository.save(history);


        response.setStatus(HttpStatus.OK.value());
        response.setDescription(HttpStatus.OK);
        response.setMessage("Success");
        return response;
    }

    @Override
    public Object read() {
        // TODO Auto-generated method stub
        ResponsDto<Object> response = new ResponsDto<>();
        List<History> checkhistory = historyRepository.findAll();
        
        if (checkhistory.isEmpty()) {
            
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setDescription(HttpStatus.NOT_FOUND);
            response.setMessage("Data not found");
            
            return response;
        }
        
        response.setStatus(HttpStatus.OK.value());
        response.setDescription(HttpStatus.OK);
        response.setMessage("Success");
        response.setResult(historyRepository.findAll());
        
        return response;
    }

    @Override
    public Object update(HistoryDto dto, Long id) {
        // TODO Auto-generated method stub
       
        ResponsDto<Object> response = new ResponsDto<>();
        Optional<History> checkHistory = historyRepository.findById(id);

        if (checkHistory.isEmpty()) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setDescription(HttpStatus.NOT_FOUND);
            response.setMessage("History not found");
            
            return response;
        }

        History history = checkHistory.get();
        if (dto.getStatus() != null) {
            history.setStatus(dto.getStatus());;
        }

        historyRepository.save(history);
        response.setStatus(HttpStatus.OK.value());
        response.setDescription(HttpStatus.OK);
        response.setMessage("Status updated");
        response.setResult(historyRepository.findById(id));

        return response;
    }
    
}
