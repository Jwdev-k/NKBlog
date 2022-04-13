package com.blog.controller;

import com.blog.api.KakaoLoginBO;
import com.blog.api.NaverLoginBO;
import com.blog.config.SessionConfig;
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
    private loginServiceimpl ls;
    @Autowired
    private NaverLoginBO NaverLoginBO;
    @Autowired
    private KakaoLoginBO KakaoLoginBO;

    private SHA256 sha256 = new SHA256();

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, HttpSession session) {
        /* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
        String naverAuthUrl = NaverLoginBO.getAuthorizationUrl(session);
        model.addAttribute("naverURL", naverAuthUrl);
        String kakaoAuthUrl = KakaoLoginBO.getAuthorizationUrl(session);
        model.addAttribute("kakaoURL", kakaoAuthUrl);
        return "login";
    }

    @RequestMapping(value = "/logincheck", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        request.setCharacterEncoding("utf-8");
        String userID = request.getParameter("userID");
        String userPassword = sha256.encrypt(request.getParameter("userPassword"));
        var u = ls.login(userID, userPassword);
        if (SessionConfig.getSessionCheck("userID", userID) == null) {
            if (u == 1) {
                session.setAttribute("userID", userID);
                return "redirect:main";
            } else if (u == 0) {
                ScriptUtils.alertAndBackPage(response, "패스워드가 틀렸습니다.");
            } else {
                ScriptUtils.alertAndBackPage(response, "존재하지 않는 아이디 입니다.");
            }
        } else {
            ScriptUtils.alertAndBackPage(response, "이미 로그인중 입니다.(중복로그인 감지)");
        }
        return null;
    }

    @RequestMapping(value = "/naverlogin", method = RequestMethod.GET)
    public String naverLogin(HttpSession session, @RequestParam("code") String code, @RequestParam("state") String state) throws IOException {
        OAuth2AccessToken oauthToken;
        oauthToken = NaverLoginBO.getAccessToken(session, code, state);
        String apiResult = NaverLoginBO.getUserProfile(oauthToken);
        //String형식인 apiResult를 json형태로 바꿈
        JsonParser jp = new JsonParser();
        JsonObject jsonobj = (JsonObject) jp.parse(apiResult);
        // 데이터 파싱
        JsonObject response_obj = (JsonObject) jsonobj.get("response");
        String userID = response_obj.get("nickname").toString().replaceAll("\"", ""); // 닉네임에 " 부분 삭제
        session.setAttribute("userID", userID);

        log.info("naver login callback");
        return "redirect:main";
    }

    @RequestMapping(value = "/kakaologin", method = RequestMethod.GET)
    public String kakaoLogin(HttpSession session, @RequestParam String code, @RequestParam String state) throws IOException {
        OAuth2AccessToken oauthToken;
        oauthToken = KakaoLoginBO.getAccessToken(session, code, state);
        String apiResult = KakaoLoginBO.getUserProfile(oauthToken);

        JsonParser jp = new JsonParser();
        JsonObject jsonobj = (JsonObject) jp.parse(apiResult);

        JsonObject response_obj = (JsonObject) jsonobj.get("kakao_account"); // 카카오 어카운트 에서 프로필 항목에 요청한 데이터가 있음.
        JsonObject profile = (JsonObject) response_obj.get("profile");
        String userID = profile.get("nickname").toString().replaceAll("\"", "");
        session.setAttribute("userID", userID);

        log.info("kakao login callback");
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
    public String register(HttpServletResponse response, HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        String userID = request.getParameter("userID");
        String userPassword = sha256.encrypt(request.getParameter("userPassword"));
        String userGender = request.getParameter("userGender");
        log.debug("user register request....");
        if (userID == null && userPassword == null && userGender == null) {
            log.debug("값이 없습니다.");
            return "redirect:join";
        } else if (ls.register(userID, userPassword, userGender)) {
            ScriptUtils.alertAndMovePage(response, "회원가입 완료.", "/NKBlog/login");
            return null;
        } else {
            ScriptUtils.alertAndBackPage(response, "이미 등록된 아이디 입니다.");
            return null;
        }
    }
}
