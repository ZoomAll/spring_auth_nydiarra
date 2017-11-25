package ru.dwfe.auth_nydiarra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.dwfe.auth_nydiarra.dao.User;
import ru.dwfe.auth_nydiarra.dao.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

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
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
    {
        User user = userRepository.findByUsername(s);

        if (user == null)
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toSet());

//        List<GrantedAuthority> authorities = new ArrayList<>();
//        user.getRoles().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        });

        return new org.springframework.security.core.userdetails.
                User(user.getUsername(), user.getPassword(), authorities);
    }
}