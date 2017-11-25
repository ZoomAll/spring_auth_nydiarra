package ru.dwfe.auth_nydiarra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dwfe.auth_nydiarra.dao.RandomCity;
import ru.dwfe.auth_nydiarra.dao.RandomCityRepository;
import ru.dwfe.auth_nydiarra.dao.User;
import ru.dwfe.auth_nydiarra.dao.UserRepository;

import java.util.List;

@Service
public class GenericServiceImpl implements GenericService
{
    private final UserRepository userRepository;

    private final RandomCityRepository randomCityRepository;

    @Autowired
    public GenericServiceImpl(UserRepository userRepository,
                              RandomCityRepository randomCityRepository)
    {
        this.userRepository = userRepository;
        this.randomCityRepository = randomCityRepository;
    }

    @Override
    public User findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers()
    {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public List<RandomCity> findAllRandomCities()
    {
        return (List<RandomCity>) randomCityRepository.findAll();
    }
}