package com.example.data.dao;

import com.example.data.enitys.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepsoitory extends JpaRepository<Users, Long> {
    Optional<Users> findByEmailIgnoreCase(String email);
}
