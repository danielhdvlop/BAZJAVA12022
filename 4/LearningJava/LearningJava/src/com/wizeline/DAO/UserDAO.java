package com.wizeline.DAO;
    public interface UserDAO {
        String CreateUser(String user, String password);
        String login(String user, String password);
    }