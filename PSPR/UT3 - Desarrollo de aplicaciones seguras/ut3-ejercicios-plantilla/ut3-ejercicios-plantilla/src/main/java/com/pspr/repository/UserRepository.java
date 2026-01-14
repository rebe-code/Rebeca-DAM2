package com.pspr.repository;

import com.pspr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Buscar un usuario por su username (lo usará Spring Security)
    Optional<User> findByUsername(String username);
}
