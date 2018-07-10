package com.pnp.controller;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pnp.dao.RoleRepository;
import com.pnp.dao.UserRepository;
import com.pnp.dao.UserRoleRepository;
import com.pnp.model.Role;
import com.pnp.model.User;
import com.pnp.model.UserRole;

@RestController
public class UserController {
	
	@Autowired
	UserRoleRepository userRoleRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@PostMapping(value = "/user/add", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<User> addUser(
			@RequestParam String email, @RequestParam(name="user_name") String userName,
			@RequestParam String password, @RequestParam(name = "role_id", required = false) @Valid @Min(value = 0) String roleId ){
				
		//Role role = roleRepo.findOne(new Role);
		Optional<Role> role = roleRepo.findById(Long.parseLong(roleId));

		User user = new User();
		user.setUsername(userName);
		user.setEmail(email);
		user.setPassword(encoder.encode(password));

		UserRole ur = new UserRole();
		ur.setRole(role.get());
		ur.setUser(user);
		
		user.getUserRoles().add(ur);
		user = userRepo.save(user);
		
		return new ResponseEntity<User>(user , HttpStatus.CREATED);
	}
	
}
