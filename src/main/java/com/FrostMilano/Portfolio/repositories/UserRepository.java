package com.FrostMilano.Portfolio.repositories;


import com.FrostMilano.Portfolio.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    List<User> findByRole(String role);
}
