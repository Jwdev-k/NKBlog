package com.blog.service.impl;

import com.blog.domain.impl.loginDAO;
import com.blog.domain.loginDTO;
import com.blog.service.loginService;
import org.springframework.stereotype.Service;

@Service
public class loginServiceimpl implements loginService {

    public static loginDAO lg = new loginDAO();

    @Override
    public int login(String uid, String password) throws Exception {
        loginDTO user;
        user = lg.login(uid);
        if (user == null || !user.getuId().equals(uid) || !user.getPassword().equals(password)) {
            System.out.println("아이디또는 패스워드 틀림.");
            return 0;
        } else {
            System.out.println(user.getuId() + " login.");
            return 1;
        }
    }

    @Override
    public void register(String userID, String userPassword, String gender) throws Exception {
        var register = lg.register(userID,userPassword,gender);
        System.out.println(register.toString());
    }
}
