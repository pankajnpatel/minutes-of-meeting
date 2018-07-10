package com.pnp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pnp.dao.RoleRepository;
import com.pnp.model.Role;
import com.pnp.model.User;

@RestController
public class RoleController {
	
	@Autowired
	RoleRepository roleRepo;
	
	@PostMapping(value = "/role/add")
	public ResponseEntity<Role> addRole(@RequestBody Role role){
		role = roleRepo.save(role);
		return new ResponseEntity<Role>(role,HttpStatus.CREATED);
	}
	@PutMapping(value = "/role/update")
	public ResponseEntity<Role> updateRole(@RequestBody Role role){
		role = roleRepo.save(role);
		return new ResponseEntity<Role>(role,HttpStatus.OK);
	}
	
	@GetMapping(value = "/role/all")
	public ResponseEntity<List<Role>> getAllRole(){
		List<Role> roleList = roleRepo.findAll();
		return new ResponseEntity<List<Role>>(roleList,HttpStatus.OK);
	}
	
	@GetMapping(value = "/role/{id}")
	public ResponseEntity<Role> getSpecificRole(@PathVariable("id") Long id){
		Role r = roleRepo.getOne(id);	
		return new ResponseEntity<Role>(r,HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/role/del/{id}")
	public ResponseEntity<String> deleteSpecificRole(@PathVariable("id") Long id){
		roleRepo.deleteById(id);
		return new ResponseEntity<String>("Role deleted successfully",HttpStatus.OK);
	}
	
	@GetMapping(value = "/role/{id}/users")
	public ResponseEntity<List<User>> getRoleUsers(@PathVariable("id") Long id){
		List<User> userList = roleRepo.findUserByRoleId(id);
		return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
	}
	
}
