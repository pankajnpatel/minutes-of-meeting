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

import com.pnp.dao.DepartmentRepository;
import com.pnp.model.Department;
import com.pnp.model.Project;
import com.pnp.model.User;

@RestController
public class DepartmentController {

	@Autowired
	DepartmentRepository deptRepo;

	@PostMapping("/dept/add")
	public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
		department = deptRepo.save(department);
		return new ResponseEntity<Department>(department, HttpStatus.OK);
	}

	@GetMapping("/dept/all")
	public ResponseEntity<List<Department>> allDepartments() {
		List<Department> deptList = deptRepo.findAll();
		return new ResponseEntity<List<Department>>(deptList, HttpStatus.OK);
	}

	@GetMapping("/dept/{id}")
	public ResponseEntity<Department> getSpecificDepartment(@PathVariable("id") Long id) {
		Department deptList = deptRepo.getOne(id);
		return new ResponseEntity<Department>(deptList, HttpStatus.OK);
	}

	@GetMapping("/dept/{id}/proj")
	public ResponseEntity<List<Project>> getDepartmentProject(@PathVariable("id") Long id) {
		List<Project> deptList = deptRepo.findProjectsByDepartmentId(id);
		return new ResponseEntity<List<Project>>(deptList, HttpStatus.OK);
	}

	@DeleteMapping("/dept/del/{id}")
	public ResponseEntity<Boolean> deleteDepartment(@PathVariable("id") Long id) {
		deptRepo.deleteById(id);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@PutMapping(value = "/dept/update")
	public ResponseEntity<Department> updateDepartment(@RequestBody Department department) {
		department = deptRepo.save(department);
		return new ResponseEntity<Department>(department, HttpStatus.OK);
	}
	
	@GetMapping("/dept/{id}/user")
	public ResponseEntity<List<User>> getDepartmentUsers(@PathVariable("id") Long id){
		List<User> userList = deptRepo.findUsersByDepartmen(id);
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}

}
