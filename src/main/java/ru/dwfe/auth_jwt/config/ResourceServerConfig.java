package ru.dwfe.auth_jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter
{
    private final TokenStore tokenStore;

    @Autowired
    public ResourceServerConfig(TokenStore tokenStore)
    {
        this.tokenStore = tokenStore;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        http
                //with JWT don't need protection from CSRF, because RESTful
                .csrf().disable()

                //with JWT don't need sessions, because RESTful
                //вообще говоря ссессия-то все равно создается: HttpServletRequest request, request.getSession()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                //for prevent exception: Cannot apply ExpressionUrlAuthorizationConfigurer
                .authorizeRequests().anyRequest().permitAll()
        ;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception
    {
        resources.tokenStore(tokenStore);
    }
}