package com.bookapp.repositories;

import java.util.Optional;

import com.bookapp.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    Optional<User> findByFacebookUserId(String facebookUserId);
}
