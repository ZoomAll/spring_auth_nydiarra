package ru.dwfe.auth_nydiarra.service;

import ru.dwfe.auth_nydiarra.dao.RandomCity;
import ru.dwfe.auth_nydiarra.dao.User;

import java.util.List;

public interface GenericService
{
    User findByUsername(String username);

    List<User> findAllUsers();

    List<RandomCity> findAllRandomCities();
}