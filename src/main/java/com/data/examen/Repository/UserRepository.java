package com.data.examen.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.examen.Entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{
	Optional<User> findByUsername(String username);
}
