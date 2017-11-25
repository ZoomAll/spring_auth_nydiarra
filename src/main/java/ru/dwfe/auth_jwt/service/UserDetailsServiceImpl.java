package ru.dwfe.auth_jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.dwfe.auth_jwt.dao.User;
import ru.dwfe.auth_jwt.dao.repository.UserRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class UserDetailsServiceImpl implements UserDetailsService
{
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException
    {
        User user = userRepository.findOne(id);

        System.out.printf("%s, user = %s%n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), id);

        if (user == null)
            throw new UsernameNotFoundException(String.format("The user '%s' doesn't exist", id));

        return user;
    }
}