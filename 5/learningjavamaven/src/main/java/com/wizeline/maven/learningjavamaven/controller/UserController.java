package com.wizeline.maven.learningjavamaven.controller;

import com.wizeline.maven.learningjavamaven.model.ResponseDTO;
import com.wizeline.maven.learningjavamaven.model.UserDTO;
import com.wizeline.maven.learningjavamaven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    @GetMapping(value = "/login", produces = "application/json")
    public ResponseEntity<ResponseDTO> login(@RequestParam String user, @RequestParam String password){
        LOGGER.info("LearningJava - Procesando Petición HTTP de tipo GET -Starting");
        ResponseDTO response;
        response = UserService.login(user,password);
        LOGGER.info("Login - Completed");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(value = "/createUser" , produces = "application/json")
    public ResponseEntity<ResponseDTO> createUser(@RequestBody UserDTO request){
        LOGGER.info("LearningJava - Procesando Petición HTTP de tipo POST -Starting");
        ResponseDTO response;
        response = userService.createUser(request.getUser(), request.getPassword());
        LOGGER.info("Create User - Completed");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<List<ResponseDTO>> createUsers(@RequestBody List<UserDTO> userDTOList) {
        AtomicReference<ResponseDTO> response = new     AtomicReference<>(new ResponseDTO());
        List<ResponseDTO> responseList = new ArrayList<>();

        userDTOList.stream().forEach( userDTO -> {
                    String user = userDTO.getUser();
                    String password = userDTO.getPassword();
                    response.set(createUser(user, password));
                    responseList.add(response.get());
                }
        );

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json; charset=UTF-8");

        return new ResponseEntity<List<ResponseDTO>>(responseList, responseHeaders, HttpStatus.OK);
    }
}
