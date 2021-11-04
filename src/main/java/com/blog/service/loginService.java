package com.blog.service;

public interface loginService {
    int login(String uid, String password) throws Exception;
    void register(String userID, String userPassword, String gender) throws Exception;
}
