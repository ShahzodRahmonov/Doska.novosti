package com.company.repository;

import com.company.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByLogin(String login);
    Optional<Users> findByLoginAndPassword(String login, String password);
}
