package com.blog.controller;

import com.blog.domain.boardDTO;
import com.blog.service.impl.boardServiceimpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

@Controller
public class BoardController {

    public static boardServiceimpl bbs = new boardServiceimpl();
    private int bno = 0;

    @RequestMapping(value = "/bbs", method = RequestMethod.GET)
    public String boardlist(){
        return "bbs";
    }

    @RequestMapping(value = "/bbs/write", method = RequestMethod.GET)
    public String boardwrite(HttpSession session){
        return "write";
    }

    @RequestMapping(value = "/bbs/write", method = RequestMethod.POST)
    public String boardwrite(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        System.out.println(title + "\n" + content + "\n" + "add board.");
        String uid = (String) session.getAttribute("userID");
        if (title != null && content != null) {
            bbs.addboard(title, content, "admin");
            return "redirect:bbs";
        } else {
            return "write";
        }
    }

    @RequestMapping(value = "/bbs/view", method = RequestMethod.GET)
    public String boardview(@RequestParam("bno") int param1, ModelMap model){
        model.addAttribute("bno", param1);
        return "view";
    }

    @RequestMapping(value = "/bbs/view/deleteAction", method = RequestMethod.GET)
    public String deleteboard(@RequestParam("bno") int param1) throws Exception {
        if (param1 != 0) {
            bbs.delteboard(param1);
        }
        return "redirect:/bbs";
    }

    @RequestMapping(value = "/bbs/view/update", method = RequestMethod.GET)
    public String boardlist(@RequestParam("bno") int param1){
        bno = param1;
        return "update";
    }

    @RequestMapping(value = "/bbs/view/update", method = RequestMethod.POST)
    public String editBoard(HttpServletRequest request) throws Exception {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        LocalDateTime now = LocalDateTime.now();
        boardDTO dto = new boardDTO(bno, title,"admin", now, content, 1);
        if (bno != 0) {
            bbs.updateboard(dto);
        }
        return "redirect:/bbs";
    }

}
