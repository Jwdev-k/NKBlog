package com.blog.service.impl;

import com.blog.domain.impl.loginDAO;
import com.blog.domain.loginDTO;
import com.blog.service.loginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class loginServiceimpl implements loginService {

    public static loginDAO lg = new loginDAO();
    public final static Logger log = LoggerFactory.getLogger(loginServiceimpl.class);

    @Override
    public int login(String uid, String password) throws Exception {
        loginDTO user = lg.login(uid, password);
        if (user == null || lg.getAccountData(uid).getPassword().equals(password)) {
            return 0;
        } else {
            log.debug(user.getUid() + " login.");
            return 1;
        }
    }

    @Override
    public void register(String userID, String userPassword, String gender) throws Exception {
        lg.register(userID, userPassword, gender);
    }
}
