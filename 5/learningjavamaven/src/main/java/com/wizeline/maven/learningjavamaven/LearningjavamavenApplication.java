package com.wizeline.maven.learningjavamaven;


import com.wizeline.maven.learningjavamaven.config.EndpointBean;
import com.wizeline.maven.learningjavamaven.service.UserService;
import com.wizeline.maven.learningjavamaven.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

@SpringBootApplication
public class LearningjavamavenApplication extends Thread {

	private static final Logger LOGGER = Logger.getLogger(LearningjavamavenApplication.class.getName());
	private static final String SUCCESS_CODE = "OK000";
	private static String responseTextThread = "";
	private static String textThread = "";

	@Autowired
	private EndpointBean endpointBean;

	@Bean
	public static UserService userService() {
		return new UserServiceImpl();
	}

	public static void main(String[] args){
		SpringApplication.run(LearningjavamavenApplication.class,args);
	}

}

