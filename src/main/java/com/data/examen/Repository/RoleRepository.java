package com.data.examen.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.examen.Entities.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
	Optional<Role> findByName(String name);
}
