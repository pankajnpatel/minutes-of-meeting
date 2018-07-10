package com.pnp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pnp.model.User;

public interface UserRepository  extends JpaRepository<User,Long>{

}