package com.github.diogocerqueiralima.usersservice.repositories;

import com.github.diogocerqueiralima.usersservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsernameOrEmail(String username, String email);

}
