package com.tanmoy.SpringSecurityJWT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanmoy.SpringSecurityJWT.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserId(int id);

	User findByUsername(String username);
}
