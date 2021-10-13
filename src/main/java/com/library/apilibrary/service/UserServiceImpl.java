package com.library.apilibrary.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.library.apilibrary.model.dto.ResponsDto;
import com.library.apilibrary.model.dto.UserDto;
import com.library.apilibrary.model.entity.User;
import com.library.apilibrary.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Object create(UserDto dto) {
        // TODO Auto-generated method stub
        ResponsDto<Object> response = new ResponsDto<>();
        
        if (dto.getUserName().length() > 20) {
            
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setDescription(HttpStatus.UNAUTHORIZED);
            response.setMessage("Name should be 20 characters or below");
            return response;
        }


        User checkName = userRepository.findByUserName(dto.getUserName());
        if (checkName != null) {
            
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setDescription(HttpStatus.UNAUTHORIZED);
            response.setMessage("Name already in use");
            return response;
            
        }
        
        
        if (userRepository.findByUserEmail(dto.getUserEmail()) != null) {
            
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setDescription(HttpStatus.UNAUTHORIZED);
            response.setMessage("Email already in use");
            return response;
            
        }

        User user = new User(dto.getUserName(), dto.getUserEmail(), passwordEncoder.encode(dto.getUserPassword()), dto.getUserRole());
        userRepository.save(user);
        response.setStatus(HttpStatus.OK.value());
        response.setDescription(HttpStatus.OK);
        response.setMessage("Success");
        response.setData(userRepository.findByUserName(dto.getUserName()));
        
        return response;
        
    }

    @Override
    public Object read() {
        // TODO Auto-generated method stub
        ResponsDto<Object> response = new ResponsDto<>();

        response.setStatus(HttpStatus.OK.value());
        response.setDescription(HttpStatus.OK);
        response.setMessage("Success");
        response.setData(userRepository.findAll());

        return response;
    }

    @Override
    public Object update(UserDto dto, Long id) {
        // TODO Auto-generated method stub
        ResponsDto<Object> response = new ResponsDto<>();
        Optional<User> checkUser = userRepository.findById(id);

        if (checkUser.isEmpty()) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setDescription(HttpStatus.NOT_FOUND);
            response.setMessage("User not found");
            
            return response;
        }

        User user = checkUser.get();
        if (dto.getUserName() != null) {
            user.setUserName(dto.getUserName());
        }

        if (dto.getUserEmail() != null) {
            user.setUserEmail(dto.getUserEmail());
        }

        if (dto.getUserPassword() != null) {
            user.setUserPassword(dto.getUserPassword());
        }

        if (dto.getUserRole() != null) {
            user.setUserRole(dto.getUserRole());
        }

        userRepository.save(user);
        response.setStatus(HttpStatus.OK.value());
        response.setDescription(HttpStatus.OK);
        response.setMessage("User successfully updated");
        response.setData(userRepository.findById(id));

        return response;
    }

    
    @Override
    public Object delete(Long id) {
        // TODO Auto-generated method stub
        ResponsDto<Object> response = new ResponsDto<>();
        Optional<User> checkUser = userRepository.findById(id);
        
        if (checkUser.isEmpty()) {
            
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setDescription(HttpStatus.NOT_FOUND);
            response.setMessage("User not found");
            
            return response;
        }
        
        User user = checkUser.get();
        user.setDeleted(true);
        userRepository.save(user);
        
        response.setStatus(HttpStatus.OK.value());
        response.setDescription(HttpStatus.OK);
        response.setMessage("User successfully updated");
        return response;
    }
    
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        // TODO Auto-generated method stub

        User user = userRepository.findByUserEmail(userEmail);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return UserDetailsImpl.build(user);
    }
}
