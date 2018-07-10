package com.pnp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pnp.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>{

}
