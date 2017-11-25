package ru.dwfe.auth_jwt;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getUsers()
    {
        return "users = ok!";
    }
}
