package com.blog.controller;

import com.blog.domain.boardDTO;
import com.blog.domain.commentDTO;
import com.blog.service.impl.boardServiceimpl;
import com.blog.service.impl.commentServiceimpl;
import com.blog.utils.ScriptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Controller
public class BoardController {

    private static final Logger log = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private boardServiceimpl bbs;
    @Autowired
    private commentServiceimpl cm;

    private int bbsID = 0;

    @RequestMapping(value = "/bbs", method = RequestMethod.GET)
    public String boardlist(Model model, @RequestParam("pageNumber") int pn) throws Exception {
        if (bbs.nextpage(pn + 1)) {
            model.addAttribute("nextPageNumber", pn + 1);
        }
        model.addAttribute("pageNumber", pn);

        ArrayList<boardDTO> list = bbs.boardList(pn);
        model.addAttribute("boardList", list);
        return "bbs";
    }

    @RequestMapping(value = "/bbs/write", method = RequestMethod.GET)
    public String boardwrite() {
        return "write";
    }

    @RequestMapping(value = "/bbs/write", method = RequestMethod.POST)
    public String boardwrite(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String title = request.getParameter("title");
        String content = request.getParameter("content").replaceAll("\n", "<br>");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        log.debug("Title: " + title + "\n" + "content: " + content + "add board.");
        String uid = (String) session.getAttribute("userID");
        if (title != null && content != null) {
            bbs.addboard(title, content, uid, LocalDate.parse(formatter.format(LocalDate.now())));
            return "redirect:" + request.getHeader("Referer");
        } else {
            return "write";
        }
    }

    @RequestMapping(value = "/bbs/view", method = RequestMethod.GET)
    public String boardview(HttpServletResponse response, @RequestParam("bno") int bno) throws IOException {
        bbsID = bno;
        if (bno == 0) {
            ScriptUtils.alertAndBackPage(response, "유효하지 않은 글입니다.");
        }
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
    public String boardUpdate(HttpServletResponse response, @RequestParam("bno") int bno) throws IOException {
        bbsID = bno;
        if (bno == 0) {
            ScriptUtils.alertAndBackPage(response, "유효하지 않는 글입니다.");
        }
        return "update";
    }

    @RequestMapping(value = "/bbs/view/update", method = RequestMethod.POST)
    public String editBoard(HttpServletRequest request, HttpSession session) throws Exception {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String uid = (String) session.getAttribute("userID");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        boardDTO dto = new boardDTO(bbsID, title, uid, LocalDate.parse(formatter.format(LocalDate.now())), content, 1);
        if (bbsID != 0) {
            bbs.updateboard(dto);
        }
        return "redirect:/bbs";
    }

    @RequestMapping(value = "/comment/add", method = RequestMethod.POST)
    public String addcomment(HttpServletRequest request, HttpSession session, @RequestParam("bno") int param1) throws Exception {
        commentDTO comment = new commentDTO(param1
                , (String) session.getAttribute("userID")
                , request.getParameter("comment"));
        log.debug("댓글등록: " + comment.toString());
        cm.addComment(comment);
        return "redirect:" + request.getHeader("Referer");
    }


}
