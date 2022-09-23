package com.wizeline.BO;

import com.wizeline.DAO.UserDAO;
import com.wizeline.DAO.UserDAOImpl;
import com.wizeline.DTO.ErrorDTO;
import com.wizeline.DTO.ResponseDTO;
import com.wizeline.Utils;

import java.util.logging.Logger;

public class UserBOImpl implements UserBO{

    private static final Logger LOGGER = Logger.getLogger(UserBOImpl.class.getName());

    @Override
    public ResponseDTO createUser(String user, String password) {
        LOGGER.info("Inicia Procesamiento en capa de negocio");
        ResponseDTO response = new ResponseDTO();
        String result = "fail";
        if(Utils.ValidateNullValue(user) && Utils.ValidateNullValue(password)){
            UserDAO userDAO = new UserDAOImpl();
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

    @Override
    public ResponseDTO login(String user, String password) {
        LOGGER.info("Inicia procesamiento en capa de negocio");
        ResponseDTO response = new ResponseDTO();
        String result = "";
        if(Utils.ValidateNullValue(user) && Utils.ValidateNullValue(password)) {
            UserDAO userDAO = new UserDAOImpl();
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
