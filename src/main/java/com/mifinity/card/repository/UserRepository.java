package com.mifinity.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mifinity.card.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}