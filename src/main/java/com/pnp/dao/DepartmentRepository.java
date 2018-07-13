package com.pnp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pnp.model.Department;
import com.pnp.model.Project;
import com.pnp.model.User;

public interface DepartmentRepository  extends JpaRepository<Department, Long>{
	
	@Query(value="Select p from Department d JOIN d.projectSet p where d.id = :id")
	List<Project> findProjectsByDepartmentId(@Param("id") Long id);
	
	@Query(value="Select u from Department d JOIN d.userSet u where d.id = :id")
	List<User> findUsersByDepartmen(@Param("id") Long id);
}
