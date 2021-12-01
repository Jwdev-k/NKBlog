package com.blog.controller;

import com.blog.service.impl.loginServiceimpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    public static loginServiceimpl ls = new loginServiceimpl();
    private boolean check = false;

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpSession session) throws Exception {
        request.setCharacterEncoding("utf-8");
        String userID = request.getParameter("userID");
        String userPassword = request.getParameter("userPassword");
        var u = ls.login(userID, userPassword);
        if (u == 1) {
            session.setAttribute("userID", userID);
            return "redirect:main";
        } else {
            check = true;
            session.invalidate();
            return "redirect:login";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "join";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(HttpServletRequest request, HttpSession session) throws Exception {
        request.setCharacterEncoding("utf-8");
        String userID = request.getParameter("userID");
        String userPassword = request.getParameter("userPassword");
        String userGender = request.getParameter("userGender");
        ls.register(userID, userPassword, userGender);
        log.debug("user register request....");
        if (userID == null || userPassword == null || userGender == null) {
            System.out.println("값이 없습니다.");
            return "redirect:join";
        } else {
            return "redirect:login";
        }
    }
}
