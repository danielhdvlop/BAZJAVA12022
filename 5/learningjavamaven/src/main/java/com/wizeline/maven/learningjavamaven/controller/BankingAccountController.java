package com.wizeline.maven.learningjavamaven.controller;

import com.wizeline.maven.learningjavamaven.client.AccountsJSONClient;
import com.wizeline.maven.learningjavamaven.model.BankAccountDTO;
import com.wizeline.maven.learningjavamaven.model.Post;
import com.wizeline.maven.learningjavamaven.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;

import static com.wizeline.maven.learningjavamaven.utils.Utils.randomAcountNumber;


@RestController
@RequestMapping("/api")
public class BankingAccountController {
    @Autowired
    private BankAccountService bankAccountService;
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
    @DeleteMapping("/deleteAccounts")
    public ResponseEntity<String> deleteAccounts() {
        bankAccountService.deleteAccounts();
        return new ResponseEntity<>("All accounts deleted", HttpStatus.OK);
    }

    @GetMapping("/getAccountByUser")
    public ResponseEntity<List<BankAccountDTO>> getAccountByUser(@RequestParam String user) {
        String msgProcPeticion = "Mensaje de inicio";
        LOGGER.info(msgProcPeticion);
        Instant inicioDeEjecucion = Instant.now();
        LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
        List<BankAccountDTO> accounts = bankAccountService.getAccountByUser(user);
        Instant finalDeEjecucion = Instant.now();
        LOGGER.info("LearningJava - Cerrando recursos ...");
        String total = new String(String.valueOf(Duration.between(inicioDeEjecucion, finalDeEjecucion).toMillis()).concat(" segundos."));
        LOGGER.info("Tiempo de respuesta: ".concat(total));
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json; charset=UTF-8");
        return new ResponseEntity<>(accounts, responseHeaders, HttpStatus.OK);
    }


    @GetMapping("/getExternalUser/{userId}")
    public ResponseEntity<String> getExternalUser(@PathVariable Long userId) {

        AccountsJSONClient accountsJSONClient = null;
        Post postTest = accountsJSONClient.getPostById(userId);
        LOGGER.info("Getting post userId..." +postTest.getUserId());
        LOGGER.info("Getting post body..." +postTest.getBody());
        LOGGER.info("Getting post title..." +postTest.getTitle());
        postTest.setUserId("External user "+randomAcountNumber());
        postTest.setBody("No info in accountBalance since it is an external user");
        postTest.setTitle("No info in title since it is an external user");
        LOGGER.info("Setting post userId..." +postTest.getUserId());
        LOGGER.info("Setting post body..." +postTest.getBody());
        LOGGER.info("Setting post title...."+postTest.getTitle());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json; charset=UTF-8");
        return new ResponseEntity<String>(String.valueOf(postTest), responseHeaders, HttpStatus.OK);
    }

}
