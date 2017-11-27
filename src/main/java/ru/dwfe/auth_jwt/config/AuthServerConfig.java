package ru.dwfe.auth_jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter
{
    private final AuthenticationManager authenticationManager;
    private final TokenStore tokenStore;
    private final AccessTokenConverter accessTokenConverter;

    @Autowired
    public AuthServerConfig(AuthenticationManager authenticationManager,
                            TokenStore tokenStore,
                            AccessTokenConverter accessTokenConverter)
    {
        this.authenticationManager = authenticationManager;
        this.tokenStore = tokenStore;
        this.accessTokenConverter = accessTokenConverter;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception
    {
        //Authorization Server: https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-security.html#boot-features-security-oauth2-authorization-server
        //Access Token Request: https://tools.ietf.org/html/rfc6749#section-4.3.2

        configurer
                .inMemory() //in case of JWT, token is NOT stored in memory!
                            //Moreover, the token is not stored anywhere on the server.

                    .withClient("Standard")
                    .secret("Login")
                    .scopes("all")
                    .accessTokenValiditySeconds(60 * 60 * 24 * 10) // 10 days

                .and()

                    .withClient("ThirdParty")
                    .secret("Computer")
                    .scopes("all")
                    .accessTokenValiditySeconds(60 * 3) // 3 minutes
        ;

        //Здесь Клиентом является Фронтэнд.
        //В качестве фронтенда может быть обычная HTML страничка + JavaScript, либо фреймворк, например, Angular.
        //Если User логинится на Клиенте, то клиент должен методом POST отправить запрос на сервер.
        //Протестировать логинг можно так:
        //curl withClient:secret@localhost:8080/oauth/token -d grant_type=password -d username=UserLogin -d password=UserPass
        //
        //В ответ придет JSON, внутри которого будет токен.
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception
    {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Arrays.asList((TokenEnhancer) accessTokenConverter));

        endpoints
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore)
                .accessTokenConverter(accessTokenConverter)
                .tokenEnhancer(enhancerChain);
    }

}