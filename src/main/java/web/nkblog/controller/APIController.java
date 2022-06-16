package web.nkblog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.nkblog.config.SessionConfig;
import web.nkblog.domain.ActiveUser;
import web.nkblog.domain.impl.loginDAO;
import web.nkblog.domain.loginDTO;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@Slf4j
@RequestMapping(value = "/api", method = RequestMethod.GET)
public class APIController {
    @Autowired
    private loginDAO ld;
    private final Gson gs = new GsonBuilder().setPrettyPrinting().create();

    @GetMapping(value = "users/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> getUser(@PathVariable("id") String uid) throws Exception {
        if (uid.equals("all")) {
            return new ResponseEntity<>(gs.toJson(ld.getAllAccountData()), HttpStatus.OK);
        } else if (ld.getAccountData(uid) != null) {
            return new ResponseEntity<>(gs.toJson(ld.getAccountData(uid)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(gs.toJson(new loginDTO("null", "null", "null", "null")), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "users/{id}/{password}/{password2}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> setPassword(@PathVariable("id") String id, @PathVariable("password") String password
            , @PathVariable("password2") String password2, HttpServletResponse response) throws Exception {
        StringBuilder sb = new StringBuilder();
        if (ld.setPassword(id, password, password2)) {
            sb.append(id).append("님의 패스워드를 ");
            sb.append(password2).append("으로 변경 하였습니다.");
            log.info(sb.toString());
            return new ResponseEntity<>(gs.toJson(sb), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(gs.toJson("failed"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "active/userlist", produces = "application/json;charset=UTF-8")
    public ArrayList<ActiveUser> userList() {
        ArrayList<ActiveUser> ac = new ArrayList<>();
        ConcurrentHashMap<String, HttpSession> chm = SessionConfig.sessionList();
        chm.forEach((k,v) -> ac.add(new ActiveUser(k, v.getAttribute("userID").toString())));
        return ac;
    }
}
