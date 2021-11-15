package com.library.apilibrary.controllers;

import java.util.Set;
import java.util.stream.Collectors;

import com.library.apilibrary.config.JWTUtils;
import com.library.apilibrary.model.dto.JWTResponse;
import com.library.apilibrary.model.dto.ResponsDto;
import com.library.apilibrary.model.dto.UserDto;
import com.library.apilibrary.model.entity.User;
import com.library.apilibrary.repository.UserRepository;
import com.library.apilibrary.service.UserDetailsImpl;
import com.library.apilibrary.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtils jwtUtils;

    @GetMapping 
    public ResponseEntity<Object> getUsers() {
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(userService.read(), HttpStatus.OK);

        return responseEntity;
    }
    @PostMapping
    public ResponseEntity<Object> addUser(@ModelAttribute UserDto dto) {
        try {
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(userService.create(dto), HttpStatus.CREATED);
            return responseEntity;
        } catch (Exception e) {
            //TODO: handle exception
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;

        }

    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@ModelAttribute UserDto dto, @PathVariable Long id) {
        try {
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(userService.update(dto, id), HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            //TODO: handle exception
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;

        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteuser(@PathVariable Long id) {
        try {
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(userService.delete(id), HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            //TODO: handle exception
            ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;

        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@ModelAttribute UserDto dto) {
        ResponsDto<Object> response = new ResponsDto<>();

        // check if user exist
        User user = userRepository.findByUserEmail(dto.getUserEmail());
        if (user != null) {
            response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
            response.setDescription(HttpStatus.EXPECTATION_FAILED);
            response.setMessage("User successfully updated");

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
        };

        // register
        try {
            Object userCreated = userService.create(dto);

            return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
        } catch (Exception e) {
            //TODO: handle exception
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setDescription(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @PostMapping("/signin")
    public ResponseEntity<Object> signIn(@RequestBody UserDto dto) {
        ResponsDto<JWTResponse> response = new ResponsDto<>();
        
        try {
            // authenticate user
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUserEmail(), dto.getUserPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // generate token
            String jwt = jwtUtils.generatetoken(authentication);

            // get user principal
            UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();

            // get role
            Set<String> roles = userDetailsImpl.getAuthorities().stream().map(role -> role.getAuthority()).collect(Collectors.toSet());

            // get user email
            String email = userDetailsImpl.getUsername();


            response.setStatus(HttpStatus.OK.value());
            response.setDescription(HttpStatus.OK);
            response.setMessage("Sign in succeed");
            // response.setResult(new JWTResponse(jwt, email, userService.getByEmail(email), roles));
            response.setResult(new JWTResponse(jwt, email, userService.getByEmail(email), roles));

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            //TODO: handle exception
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setDescription(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }
}
