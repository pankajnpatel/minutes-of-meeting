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
import com.pnp.dao.ProjectRepository;
import com.pnp.model.Department;
import com.pnp.model.Project;
import com.pnp.model.User;

@RestController
public class ProjectController {
	
	@Autowired
	ProjectRepository projectRepo;
	
	@Autowired
	DepartmentRepository departmentRepo;
	
	@PostMapping("/proj/add")
	public ResponseEntity<Project> addProject(@RequestBody Project proj){
		
		proj = projectRepo.save(proj);
		return new ResponseEntity<Project>(proj,HttpStatus.CREATED);
	}
	
	@PutMapping("/proj/update")
	public ResponseEntity<Project> updateProject(@RequestBody Project proj){
		
		proj = projectRepo.save(proj);
		return new ResponseEntity<Project>(proj,HttpStatus.OK);
	}
	
	@GetMapping("/proj/all")
	public ResponseEntity<List<Project>> allProjects(){
		
		List<Project> projList = projectRepo.findAll();
		return new ResponseEntity<List<Project>>(projList,HttpStatus.OK);
	
	}
	
	@GetMapping("/proj/dept/{id}")
	public ResponseEntity<List<Department>> projectDepartment(@PathVariable Long id){
		
		List<Department> deptList = projectRepo.findDepartmentByProjectId(id);
		return new ResponseEntity<List<Department>>(deptList,HttpStatus.OK);
	
	}

	@GetMapping("/proj/{id}")
	public ResponseEntity<Project> projectById(@PathVariable Long id){
		Project proj = projectRepo.getOne(id);
		return new ResponseEntity<Project>(proj,HttpStatus.OK);
	
	}
	
	
	@DeleteMapping("/proj/del/{id}")
	public ResponseEntity<String> deleteProject(@PathVariable Long id){
		projectRepo.deleteById(id);
		return new ResponseEntity<String>("Project deleted successfully",HttpStatus.OK);
	}
	
	@GetMapping("/proj/{id}/user")
	public ResponseEntity<List<User>> getDepartmentUsers(@PathVariable("id") Long id){
		List<User> userList = projectRepo.findUsersByProjectId(id);
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}

}
