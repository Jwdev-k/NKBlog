package com.blog.controller;

import com.blog.domain.impl.loginDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api", method = RequestMethod.GET)
public class APIController {

    private static final Logger log = LoggerFactory.getLogger(APIController.class);
    private static loginDAO ld = new loginDAO();
    private static Gson gs = new Gson();

    @GetMapping(value = "user/{id}", produces="text/plain;charset=UTF-8")
    public ResponseEntity<String> getUser(@PathVariable("id") String uid) throws Exception {
        if (uid.equals("all")) {
            JsonParser jp = new JsonParser();
            return ResponseEntity.ok().body(gs.toJson(ld.getAllAccountData()));
        } else if (ld.getAccountData(uid) != null) {
            return ResponseEntity.ok().body(gs.toJson(ld.getAccountData(uid)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
    }

    @PutMapping(value= "user/{id}/{password}/{password2}", produces="text/plain;charset=UTF-8")
    public ResponseEntity<String> setPassword(@PathVariable("id") String id, @PathVariable("password") String password
            , @PathVariable("password2") String password2, HttpServletResponse response) throws Exception {
        StringBuilder sb = new StringBuilder();
        if (ld.setPassword(id, password, password2)) {
            sb.append(id + "님의 패스워드를 ");
            sb.append(password2 + "으로 변경 하였습니다.");
            log.debug(sb.toString());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(sb.toString());
        } else {
            response.setStatus(500);
            return null;
        }
    }
}
