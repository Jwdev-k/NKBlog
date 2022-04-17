package web.nkblog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import web.nkblog.Enum.EsearchType;
import web.nkblog.domain.boardDTO;
import web.nkblog.domain.commentDTO;
import web.nkblog.service.impl.FileServiceimpl;
import web.nkblog.service.impl.boardServiceimpl;
import web.nkblog.service.impl.commentServiceimpl;
import web.nkblog.utils.PageUtil;
import web.nkblog.utils.ScriptUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Controller
public class BoardController {

    private static final Logger log = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private boardServiceimpl bbs;
    @Autowired
    private commentServiceimpl cs;
    @Autowired
    private FileServiceimpl fs;

    private int bbsID = 0;
    private PageUtil pageUtil = new PageUtil(); // 페이징 목록

    @RequestMapping(value = "/bbs", method = {RequestMethod.GET, RequestMethod.POST})
    public String boardlist(Model model, @RequestParam(value = "pageNumber", defaultValue = "1") int pn
            , @RequestParam(value = "searchType", required = false, defaultValue = "title") EsearchType type
            , @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) throws Exception {
        ArrayList<boardDTO> list = bbs.boardList(pn);
        if (!list.isEmpty()) {
            model.addAttribute("boardList", list);
        }
        pageUtil.setPage(pn);
        pageUtil.setTotalCount(bbs.countBoardList());

        model.addAttribute("pageMaker", pageUtil);
        model.addAttribute("pageNumber", pn);

        if (bbs.nextPageCheck(pn)) {
            model.addAttribute("nextPageNumber", pn + 1);
        }

        if (!keyword.equals("")) {
            ArrayList<boardDTO> list2 = bbs.searchBoard(type, keyword);
            if (!list2.isEmpty()) {
                model.addAttribute("boardList", list2);
            } else {
                model.addAttribute("boardList", null);
            }
        }
        return "bbs";
    }

    @RequestMapping(value = "/bbs/write", method = RequestMethod.GET)
    public String BoardWrite() {
        return "write";
    }

    @RequestMapping(value = "/bbs/write", method = RequestMethod.POST)
    public String BoardWrite(HttpServletRequest request, HttpSession session
            , @RequestParam(value = "image", required = false) MultipartFile file) throws Exception {
        request.setCharacterEncoding("utf-8");
        String title = request.getParameter("title");
        String uid = (String) session.getAttribute("userID");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String content = request.getParameter("content");

        if (!title.equals("") && !content.equals("")) {
            bbs.addboard(new boardDTO(0, title, uid, LocalDate.parse(formatter.format(LocalDate.now())), content, 1));
            log.debug("Title: " + title + "\n" + "content: " + content + " add board.");
            String rootDirectory = request.getSession().getServletContext().getRealPath("/");
            if (!file.isEmpty()) {
                fs.saveFile(rootDirectory, file);
            }
            return "redirect:/bbs";
        } else {
            return "write";
        }
    }

    @RequestMapping(value = "/bbs/view", method = RequestMethod.GET)
    public String BoardView(HttpServletResponse response, Model model, @RequestParam("bno") int bno
            , @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int start) throws Exception {
        bbsID = bno;
        if (bno == 0) {
            ScriptUtils.alertAndBackPage(response, "유효하지 않은 글입니다.");
        }
        var getFile = fs.getFile(bno);
        if (getFile != null) {
            model.addAttribute("File", getFile.getFilename());
            model.addAttribute("FileSize", getFile.getData().length);
        }
        model.addAttribute("boardData", bbs.getBoard(bno));
        model.addAttribute("bbsID", bno);
        ArrayList<commentDTO> commentList = cs.commentList(bno, start);
        if (!commentList.isEmpty()) {
            model.addAttribute("commentList", commentList);
        }

        return "view";
    }

    @RequestMapping(value = "/bbs/view/deleteAction", method = RequestMethod.GET)
    public String DeleteBoard(@RequestParam("bno") int param1) throws Exception {
        if (param1 != 0) {
            bbs.delteboard(param1);
        }
        return "redirect:/bbs";
    }

    @RequestMapping(value = "/bbs/view/update", method = RequestMethod.GET)
    public String EditBoard(HttpServletResponse response, Model model, @RequestParam("bno") int bno) throws Exception {
        bbsID = bno;
        if (bno == 0) {
            ScriptUtils.alertAndBackPage(response, "유효하지 않는 글입니다.");
        }
        model.addAttribute("boardData", bbs.getBoard(bno));
        return "update";
    }

    @RequestMapping(value = "/bbs/view/update", method = RequestMethod.POST)
    public String EditBoard(HttpServletRequest request, HttpSession session) throws Exception {
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
    public String AddComment(HttpServletRequest request, HttpSession session, @RequestParam("bno") int param1) throws Exception {
        commentDTO comment = new commentDTO(param1
                , (String) session.getAttribute("userID")
                , request.getParameter("comment"));
        log.debug("댓글등록: " + comment.toString());
        cs.addComment(comment);
        return "redirect:" + request.getHeader("Referer");
    }


}
