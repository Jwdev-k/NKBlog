package web.nkblog.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.nkblog.domain.impl.loginDAO;
import web.nkblog.domain.loginDTO;
import web.nkblog.service.loginService;
import web.nkblog.utils.SHA256;

import java.util.regex.Pattern;

@Slf4j
@Service
public class loginServiceimpl implements loginService {

    @Autowired
    private loginDAO lg;
    @Autowired
    private SHA256 sha256;

    @Override
    @Transactional
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
    @Transactional
    public boolean register(String userID, String userPassword, String gender) throws Exception {
        if (lg.getAccountData(userID) == null && Pattern.matches("^[a-zA-Z0-9]*$", userID) //영문 숫자만 가능
                && Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,16}", userPassword)) {
            // 8자이상 16자이내 대문자 하나 이상,소문자 하나 이상,숫자 1개 및 특수 문자 1개 이상
            lg.register(userID, sha256.encrypt(userPassword), gender); // 패스워드 단방향 암호화 후 DB로 넘겨줌
            return true;
        }
        return false;
    }
}
