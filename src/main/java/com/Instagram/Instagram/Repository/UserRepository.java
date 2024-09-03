package com.Instagram.Instagram.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Instagram.Instagram.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}