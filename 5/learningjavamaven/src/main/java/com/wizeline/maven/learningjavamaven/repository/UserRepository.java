package com.wizeline.maven.learningjavamaven.repository;

public interface UserRepository {
    String CreateUser(String user, String password);
    String login(String user, String password);
}

