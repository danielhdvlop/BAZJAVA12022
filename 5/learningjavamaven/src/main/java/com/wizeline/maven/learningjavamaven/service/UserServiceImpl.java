package com.wizeline.maven.learningjavamaven.service;

import com.wizeline.maven.learningjavamaven.model.ErrorDTO;
import com.wizeline.maven.learningjavamaven.model.ResponseDTO;
import com.wizeline.maven.learningjavamaven.repository.UserRepository;
import com.wizeline.maven.learningjavamaven.repository.UserRepositoryImpl;
import com.wizeline.maven.learningjavamaven.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

    @Override
    public ResponseDTO createUser(String user, String password) {
        LOGGER.info("Inicia Procesamiento en capa de negocio");
        ResponseDTO response = new ResponseDTO();
        String result = "fail";
        if(Utils.ValidateNullValue(user) && Utils.ValidateNullValue(password)){
            UserRepository userDAO = new UserRepositoryImpl();
            result = userDAO.CreateUser(user,password);
            response.setCode("0K000");
            response.setStatus(result);
        } else {
            response.setCode("0K001");
            response.setStatus(result);
            response.setErrors(new ErrorDTO("ER001","Error al crear usuario"));
        }
        return response;
    }

    public ResponseDTO login(String user, String password) {
        LOGGER.info("Inicia procesamiento en capa de negocio");
        ResponseDTO response = new ResponseDTO();
        String result = "";
        if(Utils.ValidateNullValue(user) && Utils.ValidateNullValue(password)) {
            UserRepository userDAO = new UserRepositoryImpl();
            result = userDAO.login(user, password);
        }
        if("success".equals(result)){
            response.setCode("0K000");
            response.setStatus(result);
        } else {
            response.setCode("0K001");
            response.setStatus(result);
            response.setErrors(new ErrorDTO("ER001","Error al crear usuario"));
        }
        return response;
    }
}
