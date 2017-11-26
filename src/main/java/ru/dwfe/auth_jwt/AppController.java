package ru.dwfe.auth_jwt;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController
{
    @RequestMapping(value = "/cities")
    @PreAuthorize("hasAuthority('USER')")
    public String getUser()
    {
        return "cities = ok!";
    }

    @RequestMapping(value = "/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getUsers()
    {
        return "users = ok!";
    }

    @RequestMapping(value = "/bcrypt")
    public String bcrypt()
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode("passAdmin");
    }
}
