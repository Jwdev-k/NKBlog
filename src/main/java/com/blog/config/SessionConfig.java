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

    public synchronized static String getSessionCheck(String type, String compareID){
        String result = null;
        for(String key : sessions.keySet()) { // 세션에 값이 있는지 확인
            HttpSession hs = sessions.get(key);
            if (hs != null && hs.getAttribute(type) != null && hs.getAttribute(type).toString().equals(compareID)) {
                result = key;
            }
        }
        if (result != null ) {
            removeSessionDoubleLogin(result);
        }
        return result;
    }

    private static void removeSessionDoubleLogin(String userID) {
        log.debug("remove session userID: " + userID);
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        sessions.put(httpSessionEvent.getSession().getId(), httpSessionEvent.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        if(sessions.get(httpSessionEvent.getSession().getId()) != null) {
            sessions.get(httpSessionEvent.getSession().getId()).invalidate();
            sessions.remove(httpSessionEvent.getSession().getId());
        }
    }
}
