package com.assignment.spring.repository;

import java.util.Optional;

import com.assignment.spring.models.ERole;
import com.assignment.spring.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
