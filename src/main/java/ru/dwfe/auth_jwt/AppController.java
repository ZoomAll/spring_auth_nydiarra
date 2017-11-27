package ru.dwfe.auth_jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController
{
    private final TokenStore tokenStore;

    @Autowired
    public AppController(TokenStore tokenStore)
    {
        this.tokenStore = tokenStore;
    }

    @RequestMapping("/cities")
    @PreAuthorize("hasAuthority('USER')")
    public String getUser()
    {
        return "cities = ok!";
    }

    @RequestMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getUsers()
    {
        return "users = ok!";
    }

    @RequestMapping("/bcrypt")
    public String bcrypt()
    {
        System.out.println();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode("passAdmin");
    }

    @RequestMapping("/lo")
    public String lo(OAuth2Authentication auth)
    {
        if (auth == null)
            return "user is not logged";

        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
        String tokenValue = details.getTokenValue();
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(tokenValue);
        tokenStore.removeAccessToken(oAuth2AccessToken);

        return "token revoke status = done";
    }

    @RequestMapping("/")
    public String index()
    {
        return "Hello Index";
    }

}
