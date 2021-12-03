package com.blog.service.impl;

import com.blog.domain.impl.loginDAO;
import com.blog.domain.loginDTO;
import com.blog.service.loginService;
import org.springframework.stereotype.Service;

@Service
public class loginServiceimpl implements loginService {

    public static loginDAO lg = new loginDAO();

    @Override
    public int login(String uid, String password) {
        loginDTO user;
        user = lg.login(uid, password);
        if (user == null || !user.getuId().equals(uid) || !user.getPassword().equals(password)) {
            return 0;
        } else {
            System.out.println(user.getuId() + " login.");
            return 1;
        }
    }

    @Override
    public void register(String userID, String userPassword, String gender) throws Exception {
        lg.register(userID, userPassword, gender);
    }
}
