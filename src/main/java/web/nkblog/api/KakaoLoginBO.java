package com.blog.api;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@Component
public class KakaoLoginBO {

    // 카카오 로그인 정보
    private final static String KAKAO_CLIENT_ID = "7237b3a2ad2f80512700b536f2f6910b";
    private final static String KAKAO_CLIENT_SECRET = "iaNnIAg3JKjKNRga16OtEYAV2ErF1x1n";
    private final static String KAKAO_REDIRECT_URI = "http://localhost:8585/NKBlog/kakaologin";

    private final static String SESSION_STATE = "kakao_oauth_state";
    private final static String PROFILE_API_URL = "https://kapi.kakao.com/v2/user/me";

    public String getAuthorizationUrl(HttpSession session) {
        String state = generateRandomString();
        setSession(session, state);
        OAuth20Service oauthService = new ServiceBuilder().apiKey(KAKAO_CLIENT_ID).apiSecret(KAKAO_CLIENT_SECRET)
                .callback(KAKAO_REDIRECT_URI)
                .state(state)
                .build(KakaoLoginApi.instance());
        return oauthService.getAuthorizationUrl();
    }

    public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws IOException {
        String sessionState = getSession(session);
        if (StringUtils.pathEquals(sessionState, state)) {
            OAuth20Service oauthService = new ServiceBuilder()
                    .apiKey(KAKAO_CLIENT_ID)
                    .apiSecret(KAKAO_CLIENT_SECRET)
                    .callback(KAKAO_REDIRECT_URI)
                    .state(state)
                    .build(KakaoLoginApi.instance());
            return oauthService.getAccessToken(code);
        }
        return null;
    }

    public String getUserProfile(OAuth2AccessToken oauthToken) throws IOException {
        OAuth20Service oauthService = new ServiceBuilder()
                .apiKey(KAKAO_CLIENT_ID)
                .apiSecret(KAKAO_CLIENT_SECRET)
                .callback(KAKAO_REDIRECT_URI)
                .build(KakaoLoginApi.instance());
        OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauthService);
        oauthService.signRequest(oauthToken, request);
        Response response = request.send();
        return response.getBody();
    }

    private String generateRandomString() {
        return UUID.randomUUID().toString();
    }

    private void setSession(HttpSession session, String state) {
        session.setAttribute(SESSION_STATE, state);
    }

    private String getSession(HttpSession session) {
        return (String) session.getAttribute(SESSION_STATE);
    }
}