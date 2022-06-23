package web.nkblog.config;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
@Slf4j
public class SessionConfig implements HttpSessionListener {

    private static final ConcurrentHashMap<String, HttpSession> sessions = new ConcurrentHashMap<>();

    public synchronized static String getSessionCheck(String type, String compareID) { //type = 세션이름, compareID = 세션값
        String result = null;
        for (String key : sessions.keySet()) { // 세션에 값이 있는지 확인
            if (key != null) {
                HttpSession hs = sessions.get(key);
                if (hs != null && hs.getAttribute(type) != null && hs.getAttribute(type).toString().equals(compareID)) {
                    result = key;
                }
            }
        }
        return result;
    }

    public static void sessionRemove(String result) {
        if (result != null) {
            log.info("remove session userID: " + result);
            sessions.get(result).invalidate();
            sessions.remove(result);
        }
    }

    public static ConcurrentHashMap<String, HttpSession> sessionList() {
        return sessions;
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) { // 맵에 세션 저장
        log.info("세션 추가");
        sessions.put(httpSessionEvent.getSession().getId(), httpSessionEvent.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) { //세션 삭제시 맵 에 있는지 확인후
        log.info("세션 삭제");
        if (sessions.get(httpSessionEvent.getSession().getId()) != null) {
            sessions.get(httpSessionEvent.getSession().getId()).invalidate();
            sessions.remove(httpSessionEvent.getSession().getId());
        }
    }
}
