package ru.dwfe.auth_nydiarra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.dwfe.auth_nydiarra.dao.RandomCity;
import ru.dwfe.auth_nydiarra.dao.User;
import ru.dwfe.auth_nydiarra.service.GenericService;

import java.util.List;

@RestController
@RequestMapping("/springjwt")
public class AppController
{
    private final GenericService userService;

    @Autowired
    public AppController(GenericService userService)
    {
        this.userService = userService;
    }

    @RequestMapping(value ="/cities")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public List<RandomCity> getUser(){
        return userService.findAllRandomCities();
    }

    @RequestMapping(value ="/users", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public List<User> getUsers(){
        return userService.findAllUsers();
    }
}
