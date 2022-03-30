package com.blog.controller;

import com.blog.api.NaverLoginBO;
import com.blog.service.impl.loginServiceimpl;
import com.blog.utils.SHA256;
import com.blog.utils.ScriptUtils;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    loginServiceimpl ls;
    @Autowired
    NaverLoginBO NaverLoginBO;
    private String apiResult = null;

    SHA256 sha256 = new SHA256();

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, HttpSession session) {
        /* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
        String naverAuthUrl = NaverLoginBO.getAuthorizationUrl(session);
        model.addAttribute("url", naverAuthUrl);
        return "login";
    }

    @RequestMapping(value = "/logincheck", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        request.setCharacterEncoding("utf-8");
        String userID = request.getParameter("userID");
        String userPassword = sha256.encrypt(request.getParameter("userPassword"));
        if (session.getAttribute("userID") != null) {
            session.removeAttribute("userID");
        }
        var u = ls.login(userID, userPassword);
        if (u == 1) {
            session.setAttribute("userID", userID);
            return "redirect:main";
        } else {
            ScriptUtils.alertAndBackPage(response, "아이디또는 패스워드가 틀렸습니다.");
        }
        return null;
    }

    @RequestMapping(value="/loginchecknaver", method=RequestMethod.GET)
    public String loginnaver(HttpSession session, @RequestParam("code") String code, @RequestParam("state") String state) throws IOException {
        OAuth2AccessToken oauthToken;
        oauthToken = NaverLoginBO.getAccessToken(session, code, state);
        apiResult = NaverLoginBO.getUserProfile(oauthToken);
        //String형식인 apiResult를 json형태로 바꿈
        JsonParser jp = new JsonParser();
        Object obj = jp.parse(apiResult);
        JsonObject jsonobj = (JsonObject) obj;
        //3. 데이터 파싱
        JsonObject response_obj = (JsonObject) jsonobj.get("response");
        String userID = response_obj.get("nickname").toString().replaceAll("[^\\uAC00-\\uD7A30-9a-zA-Z]", "");

        session.setAttribute("userID", userID);

        log.info("naver login callback");
        return "redirect:main";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public void logout(HttpServletResponse response, HttpSession session) throws Exception {
        session.invalidate();
        ScriptUtils.alertAndMovePage(response, "로그아웃 되었습니다.", "/NKBlog/main");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "join";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(HttpServletRequest request, HttpSession session) throws Exception {
        request.setCharacterEncoding("utf-8");
        String userID = request.getParameter("userID");
        String userPassword = sha256.encrypt(request.getParameter("userPassword"));
        String userGender = request.getParameter("userGender");
        ls.register(userID, userPassword, userGender);
        log.debug("user register request....");
        if (userID == null && userPassword == null && userGender == null) {
            log.debug("값이 없습니다.");
            return "redirect:join";
        } else {
            return "redirect:login";
        }
    }
}
