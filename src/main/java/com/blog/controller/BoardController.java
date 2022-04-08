package com.blog.controller;

import com.blog.Enum.EsearchType;
import com.blog.domain.boardDTO;
import com.blog.domain.commentDTO;
import com.blog.service.impl.boardServiceimpl;
import com.blog.service.impl.commentServiceimpl;
import com.blog.utils.PageMaker;
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
    private PageMaker pageMaker = new PageMaker(); // 페이징 목록

    @RequestMapping(value = "/bbs", method = {RequestMethod.GET,RequestMethod.POST})
    public String boardlist(HttpServletResponse response, Model model
            , @RequestParam(value = "pageNumber", defaultValue = "1") int pn
            ,@RequestParam(value = "searchType", defaultValue = "title")EsearchType type
            ,@RequestParam(value = "keyword", required = false, defaultValue = "")String keyword) throws Exception {
        ArrayList<boardDTO> list = bbs.boardList(pn);
        if (!list.isEmpty()) {
            model.addAttribute("boardList", list);
        }
        pageMaker.setPage(pn);
        pageMaker.setTotalCount(bbs.countBoardList());

        model.addAttribute("pageMaker", pageMaker);
        model.addAttribute("pageNumber", pn);

        if (bbs.nextPageCheck(pn)) {
            model.addAttribute("nextPageNumber", pn + 1);
        }

        if (!keyword.equals("")) {
            ArrayList<boardDTO> list2 = bbs.searchBoard(type, keyword);
            if (!list2.isEmpty()) {
                model.addAttribute("boardList", list2);
            }
        }
        return "bbs";
    }

    @RequestMapping(value = "/bbs/write", method = {RequestMethod.GET,RequestMethod.POST})
    public String boardWrite(HttpServletRequest request, HttpSession session) throws Exception {
        request.setCharacterEncoding("utf-8");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        log.debug("Title: " + title + "\n" + "content: " + content + "add board.");
        String uid = (String) session.getAttribute("userID");
        if (title != null && content != null) {
            bbs.addboard(title, content, uid, LocalDate.parse(formatter.format(LocalDate.now())));
            return "redirect:/bbs";
        } else {
            return "write";
        }
    }

    @RequestMapping(value = "/bbs/view", method = RequestMethod.GET)
    public String boardView(HttpServletResponse response, Model model, @RequestParam("bno") int bno) throws Exception {
        bbsID = bno;
        if (bno == 0) {
            ScriptUtils.alertAndBackPage(response, "유효하지 않은 글입니다.");
        }
        model.addAttribute("boardData", bbs.getBoard(bno));
        return "view";
    }

    @RequestMapping(value = "/bbs/view/deleteAction", method = RequestMethod.GET)
    public String deleteBoard(@RequestParam("bno") int param1) throws Exception {
        if (param1 != 0) {
            bbs.delteboard(param1);
        }
        return "redirect:/bbs";
    }

    @RequestMapping(value = "/bbs/view/update", method = RequestMethod.GET)
    public String editBoard(HttpServletResponse response, Model model, @RequestParam("bno") int bno) throws Exception {
        bbsID = bno;
        if (bno == 0) {
            ScriptUtils.alertAndBackPage(response, "유효하지 않는 글입니다.");
        }
        model.addAttribute("boardData", bbs.getBoard(bno));
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
    public String addComment(HttpServletRequest request, HttpSession session, @RequestParam("bno") int param1) throws Exception {
        commentDTO comment = new commentDTO(param1
                , (String) session.getAttribute("userID")
                , request.getParameter("comment"));
        log.debug("댓글등록: " + comment.toString());
        cm.addComment(comment);
        return "redirect:" + request.getHeader("Referer");
    }


}
