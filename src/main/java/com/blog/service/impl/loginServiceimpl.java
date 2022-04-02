package com.blog.service.impl;

import com.blog.domain.impl.loginDAO;
import com.blog.domain.loginDTO;
import com.blog.service.loginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class loginServiceimpl implements loginService {

    public static loginDAO lg = new loginDAO();

    @Override
    public int login(String uid, String password) throws Exception {
        loginDTO user = lg.login(uid, password);
        if (lg.getAccountData(uid) == null) {
            return -1;
        } else if (user == null) {
            return 0;
        } else {
            log.debug(user.getUid() + " login.");
            return 1;
        }
    }

    @Override
    public boolean register(String userID, String userPassword, String gender) throws Exception {
        if (lg.getAccountData(userID) == null) {
            lg.register(userID, userPassword, gender);
            return true;
        }
        return false;
    }
}
