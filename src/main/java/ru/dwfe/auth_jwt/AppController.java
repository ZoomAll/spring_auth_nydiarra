package ru.dwfe.auth_jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class AppController
{
    private final TokenStore tokenStore;

    private final AccessTokenConverter accessTokenConverter;

    @Autowired
    public AppController(TokenStore tokenStore, AccessTokenConverter accessTokenConverter)
    {
        this.tokenStore = tokenStore;
        this.accessTokenConverter = accessTokenConverter;
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
    public String lo(OAuth2Authentication auth) throws IOException
    {
        if (auth == null)
            return "user is not logged";

        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
        String tokenValue = details.getTokenValue();

        JsonParser parser = JsonParserFactory.getJsonParser();
        Map<String, ?> claims = parser.parseMap(JwtHelper.decode(tokenValue).getClaims());

        String customField = (String) claims.get("user_name");

        System.out.println(claims);

        //OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(tokenValue);
        //tokenStore.removeAccessToken(oAuth2AccessToken);
        //User principal = (User) ((OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication()).getUserAuthentication().getPrincipal();

        return "token revoke status = done";
    }

    @RequestMapping("/")
    public String index()
    {
        return "Hello Index";
    }

}
