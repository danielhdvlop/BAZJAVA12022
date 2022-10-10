package com.wizeline.maven.learningjavamaven.service;


import com.wizeline.maven.learningjavamaven.model.ResponseDTO;

public interface UserService {
    ResponseDTO createUser(String user, String password);
    static ResponseDTO login(String user, String password);
}
