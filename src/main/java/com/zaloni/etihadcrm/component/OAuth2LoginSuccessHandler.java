package com.zaloni.etihadcrm.component;

import com.zaloni.etihadcrm.oauth2.GithubOAuth2User;
import com.zaloni.etihadcrm.util.CookiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    OAuth2AuthorizedClientService clientService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
                oauthToken.getAuthorizedClientRegistrationId(),
                oauthToken.getName());
        OAuth2AccessToken accessToken = client.getAccessToken();
//        CookiesUtil.add("tokenValue", accessToken.getTokenValue(), response);
//        CookiesUtil.add("tokenType", accessToken.getTokenValue(), response);
//        CookiesUtil.add("tokenExpiresAt", accessToken.getExpiresAt().toString(), response);
//        CookiesUtil.add("tokenIssuedAt", accessToken.getIssuedAt().toString(), response);
        System.out.println(accessToken.getTokenValue());
        System.out.println(accessToken.getTokenType().getValue());
        System.out.println(accessToken.getExpiresAt());
        System.out.println(accessToken.getIssuedAt());
        System.out.println(accessToken.getScopes());
        super.onAuthenticationSuccess(request, response, authentication);
    }
}