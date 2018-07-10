package com.pnp.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pnp.model.Role;
import com.pnp.model.User;

public interface RoleRepository  extends JpaRepository<Role,Long>{

	@Query(value = "select u from Role r join r.userSet u where r.id = :id")
	List<User> findUserByRoleId(@Param("id") Long id);
	
}