package com.wizeline.maven.learningjavamaven.repository;


import org.springframework.stereotype.Repository;

import java.io.*;
        import java.util.logging.Logger;
@Repository
public class UserRepositoryImpl implements UserRepository{

    private static final Logger LOGGER = Logger.getLogger(UserRepositoryImpl.class.getName());

    @Override
    public String CreateUser(String user, String password) {
        createFile();
        LOGGER.info("Inicia Procesamiento en capa de acceso de datos");
        LOGGER.info("Inicia proceso de alta de usuario en BD...");

        writeFile(user,password);

        LOGGER.info("Alta exitosa");
        return "success";
    }

    @Override
    public String login(String user, String password) {
        createFile();
        LOGGER.info("Inicia Procesamiento en capa de acceso de datos");
        LOGGER.info("Inicia proceso de login...");
        if("success".equals(readFile(user,password))){
            LOGGER.info("Login exitoso");
            return "success";
        } else
            return "Usuario o password incorrecto";

    }
    private void createFile(){
        try{
            File myObj = new File("file.txt");
            if(myObj.createNewFile())
                LOGGER.info("Archivo Creado" + myObj.getName());
            else
                LOGGER.info("El Archivo ya Existe" + myObj.getName());

        } catch (IOException e) {
            LOGGER.info("Ocurrió un error inesperado");
            throw new RuntimeException(e);
        }
    }
    private void writeFile(String user, String password){
        try {
            File file = new File("file.txt");
            if (file.createNewFile())
                LOGGER.info("Archivo Creado" + file.getName());
            else
                LOGGER.info("El Archivo ya Existe" );
            FileWriter fileWriter = new FileWriter(file.getName(),true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(user+", "+password);
            bw.newLine();
            bw.close();
            LOGGER.info("Se ecribió correctamente en el archivo");
        } catch (IOException e) {
            LOGGER.info("Ocurrió un error inesperado");
            throw new RuntimeException(e);
        }
    }
    private String readFile(String user, String password){
        String result = "fail";
        try {
            File file = new File ("file.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                if(line.contains(user) && line.contains(password))
                    result = "success";
            }
        } catch (IOException e) {
            LOGGER.info("Ocurrió un error inesperado");
            throw new RuntimeException(e);
        }
        return result;
    }
}
