package web.nkblog.service;

import web.nkblog.domain.loginDTO;

public interface loginService {
    int login(String uid, String password) throws Exception;
    boolean register(loginDTO user) throws Exception;
    loginDTO getAccountData(String uid) throws Exception;
    String getUserEmail(String email) throws Exception;
}
