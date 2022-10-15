package com.wizeline.maven.learningjavamaven.controller;

import com.wizeline.maven.learningjavamaven.model.ResponseDTO;
import com.wizeline.maven.learningjavamaven.model.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerTEST {
    private static final Logger LOGGER = Logger.getLogger(UserControllerTEST.class.getName());
    int data = 1;
    private int codigoServ = 0;
    private String password = null;
    private String user = null;
    @Mock
    ResponseEntity<ResponseDTO> response;
    @Mock
    ResponseEntity<List<ResponseDTO>>  responseList;
    @Mock
    private UserDTO userDTO;
    @Autowired
    private UserController userController;
    @BeforeEach
    void antesPrueba() {
        data = 0;
        LOGGER.info("Antes de cada prueba " + data);

        if (user == null & password == null) {
            user = "daniel.vargaslo@elektra.com.mx";
            password = "Passwrd001";
        }
    }
    @Test
    @DisplayName("Prueba para HAPPY PATH")
    public void happyPath() {

        LOGGER.info("Inica el servicio del Camino Feliz de login");
        response = userController.login(user, password);
        codigoServ = response.getStatusCodeValue();
        LOGGER.info("Respuesta Login:" + response.getStatusCodeValue());
        assertEquals(200, codigoServ);
    }

    @Test
    @DisplayName("Prueba Login")
    public void pruebaLogin() {
        LOGGER.info("Iniciando login");
        response = userController.login(user, password);
        codigoServ = response.getStatusCodeValue();
        LOGGER.info("Respuesta: " + response.getStatusCodeValue());
        assertEquals(200,codigoServ);
    }

    @Test
    @DisplayName("Prueba Login (Error)")
    public void pruebaLoginError() {
        LOGGER.info("Iniciando Login incorrecto");
        response = userController.login(user, "1235214");
        String codResp = response.getBody().getCode();
        LOGGER.info("Respuesta: " + response.getStatusCodeValue());
        assertEquals("ER001", codResp,"Error al hacer login");
    }

    @Test
    @DisplayName("Prueba Crear Usuario")
    public void pruebaCrearUsuario() {
        LOGGER.info("Iniciando Crear Usuario");
        userDTO.setUser(user);
        userDTO.setPassword(password);
        response = userController.createUser(userDTO);
        LOGGER.info("Respuesta:" + response.getStatusCodeValue());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Prueba Crear Usuario (Error)")
    public void pruebaCrearUsuarioError() {
        password = null;
        user = null;
        LOGGER.info("Iniciando Crear Usuario con error");
        response = userController.createUser(userDTO);
        String codigoResp = response.getBody().getStatus();
        LOGGER.info("Respuesta: " + response.getStatusCodeValue());
        assertEquals("fail", codigoResp, "No se pudeo crear el usuario");
    }

}
