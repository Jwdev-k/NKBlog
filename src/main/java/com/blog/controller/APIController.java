package com.blog.controller;

import com.blog.domain.impl.loginDAO;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class APIController {

    private static loginDAO ld = new loginDAO();
    private static Gson gs = new Gson();

    @GetMapping(value = "/getAccount", produces="text/plain;charset=UTF-8")
    public String getUser(@RequestParam("uid") String uid) throws Exception {
        if (uid.equals("all")) {
            return gs.toJson(ld.getAllAccountData());
        } else {
            return gs.toJson(ld.getAccountData(uid));
        }
    }
}
