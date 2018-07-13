package com.pnp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pnp.model.Department;
import com.pnp.model.Project;
import com.pnp.model.User;

public interface ProjectRepository  extends JpaRepository<Project, Long>{

	@Query(value="select d from Project p JOIN p.deptSet d where p.id = :id" )
	List<Department> findDepartmentByProjectId(@Param("id") long id);
	
	@Query(value="select u from Project p JOIN p.userSet u where p.id = :id" )
	List<User> findUsersByProjectId(@Param("id") Long id);
	
}
