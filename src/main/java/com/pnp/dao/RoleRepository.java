package com.pnp.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pnp.model.Role;

public interface RoleRepository  extends JpaRepository<Role,Long>{

}