package com.blog.controller;

import com.blog.domain.boardDTO;
import com.blog.service.impl.boardServiceimpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.PrintStream;

@Controller
public class BoardController {

    public static boardServiceimpl bbs = new boardServiceimpl();

    @RequestMapping(value = "/bbs", method = RequestMethod.GET)
    public String boardlist(){
        return "bbs";
    }

    @RequestMapping(value = "/bbs/write", method = RequestMethod.GET)
    public String boardwrite(){
        return "write";
    }

    @RequestMapping(value = "/bbs/write", method = RequestMethod.POST)
    public String boardwrite(HttpServletRequest request, HttpSession session){
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        System.out.println(title + "\n" + content + "\n" + "add board.");
        String uid = (String) session.getAttribute("userID");
        if (title != null && content != null) {
            bbs.addboard(title, content, "admin");
            return "bbs";
        } else {
            return "write";
        }
    }
}
