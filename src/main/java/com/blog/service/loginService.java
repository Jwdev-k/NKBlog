package com.blog.service;

public interface loginService {
    int login(String uid, String password);
    void register(String userID, String userPassword, String gender) throws Exception;
}
