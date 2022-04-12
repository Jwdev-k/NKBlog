package web.nkblog.service;

public interface loginService {
    int login(String uid, String password) throws Exception;
    boolean register(String userID, String userPassword, String gender) throws Exception;
}
