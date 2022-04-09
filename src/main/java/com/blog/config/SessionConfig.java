package com.blog.config;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
@Slf4j
public class SessionConfig implements HttpSessionListener {

    private static final Map<String, HttpSession> sessions = new ConcurrentHashMap<>();

    public synchronized static String getSessionCheck(String type, String compareID){ //type = 세션이름, compareID = 세션값
        String result = null;
        for(String key : sessions.keySet()) { // 세션에 값이 있는지 확인
            HttpSession hs = sessions.get(key);
            if (hs != null && hs.getAttribute(type) != null && hs.getAttribute(type).toString().equals(compareID)) {
                result = key;
            }
        }
        if (result != null ) {
            removeSessionDoubleLogin(result); // 조건문으로 값이 없을경우만 등록되게 구현했기 때문에 단순 로그.
        }
        return result;
    }

    private static void removeSessionDoubleLogin(String userID) { //
        log.debug("remove session userID: " + userID);
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) { // 맵에 세션 저장
        sessions.put(httpSessionEvent.getSession().getId(), httpSessionEvent.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) { //세션 삭제시 맵 에 있는지 확인후
        if(sessions.get(httpSessionEvent.getSession().getId()) != null) {
            sessions.get(httpSessionEvent.getSession().getId()).invalidate();
            sessions.remove(httpSessionEvent.getSession().getId());
        }
    }
}
