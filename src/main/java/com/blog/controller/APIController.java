package com.blog.controller;

import com.blog.domain.impl.loginDAO;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class APIController {

    private static loginDAO ld = new loginDAO();
    private static Gson gs = new Gson();

    @GetMapping(value = "user/{id}", produces="text/plain;charset=UTF-8")
    public String getUser(@PathVariable("id") String uid) throws Exception {
        if (uid.equals("all")) {
            return gs.toJson(ld.getAllAccountData());
        } else {
            return gs.toJson(ld.getAccountData(uid));
        }
    }
}
