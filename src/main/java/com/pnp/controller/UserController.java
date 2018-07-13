package com.pnp.controller;

import java.util.List;

import javassist.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pnp.dao.RoleRepository;
import com.pnp.dao.UserMeetingRepository;
import com.pnp.dao.UserRepository;
import com.pnp.model.Department;
import com.pnp.model.Meeting;
import com.pnp.model.Project;
import com.pnp.model.Role;
import com.pnp.model.User;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	BCryptPasswordEncoder encoder;

	@Autowired
	UserMeetingRepository userMeetingRepo;

	
	@PostMapping("/user/add") 
	public ResponseEntity<User> addUser(@RequestBody User user) { 
		
		user.setPassword(encoder.encode(user.getPassword()));
		user = userRepo.save(user);
		return new ResponseEntity<User>(user,HttpStatus.CREATED);
	}
	
	@PutMapping("/user/update") 
	public ResponseEntity<User> updateUser(@RequestBody User user) throws NotFoundException {
		
		if (user.getId() != 0l && user.getId() != null) {
			user = userRepo.save(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			throw new NotFoundException("User not found");
		}
	}
	
	@GetMapping("/user/all")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> userList = userRepo.findAll();
		return new ResponseEntity<List<User>>(userList,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getSpecificUser(@PathVariable("id") Long id) {
		User user = userRepo.getOne(id);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@DeleteMapping("/user/delete/{id}")
	public ResponseEntity<String> deleteSpecificUser(@PathVariable("id") Long id) {
		userRepo.deleteById(id);
		return new ResponseEntity<String>("User deleted successfully",HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}/dept")
	public ResponseEntity<List<Department>> getUserDepartments(@PathVariable("id") Long id) {
		List<Department> deptList = userRepo.findDepartmentsByUserId(id);
		return new ResponseEntity<List<Department>>(deptList,HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}/proj")
	public ResponseEntity<List<Project>> getUserProjects(@PathVariable("id") Long id) {
		List<Project> projList = userRepo.findProjectsByUserId(id);
		return new ResponseEntity<List<Project>>(projList,HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}/role")
	public ResponseEntity<List<Role>> getUserRoles(@PathVariable("id") Long id) {
		List<Role> roleList = userRepo.findRolesByUserId(id);
		return new ResponseEntity<List<Role>>(roleList,HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}/meeting")
	public ResponseEntity<List<Meeting>> getUserMeetings(@PathVariable("id") Long id) {
		List<Meeting> meetingList = userRepo.findMeetingByUserId(id);
		return new ResponseEntity<List<Meeting>>(meetingList,HttpStatus.OK);
	}
	
	@GetMapping("/dept/{deptId}/proj/{projId}")
	public ResponseEntity<List<User>> getUserMeetings(@PathVariable("deptId") Long deptId , @PathVariable("projId") Long projId) {
		List<User> userList = null;
		
		if( deptId != 0 && projId != 0 ){
			userList = userRepo.findUserByProjectIdAndDeptmentId(deptId,projId);
		}else if(deptId != 0){
			userList = userRepo.findUserByDeptmentId(deptId);
		}else if(projId != 0){
			userList = userRepo.findUserByProjectId(projId);
		}
		
		return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
	}
	
	@GetMapping("/user-taker/{id}/meeting")
	public ResponseEntity<List<Meeting>> getMeetingTkrMeetings(@PathVariable("id") Long id) {
		List<Meeting> meetingList = userRepo.findMeetingByMeetingTkr(id);
		return new ResponseEntity<List<Meeting>>(meetingList,HttpStatus.OK);
	}
}
