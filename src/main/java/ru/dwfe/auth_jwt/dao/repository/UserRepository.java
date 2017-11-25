package ru.dwfe.auth_jwt.dao.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dwfe.auth_jwt.dao.User;

public interface UserRepository extends CrudRepository<User, String>
{
}