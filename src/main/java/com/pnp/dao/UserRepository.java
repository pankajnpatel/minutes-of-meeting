package com.pnp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pnp.model.Department;
import com.pnp.model.Meeting;
import com.pnp.model.Project;
import com.pnp.model.Role;
import com.pnp.model.User;

public interface UserRepository  extends JpaRepository<User,Long>{

	public User findByusername(String username);
	
	@Query(value="select d from User u inner join u.userDept d where u.id = :id" )
	public List<Department> findDepartmentsByUserId(@Param("id") Long id);
	
	@Query(value="select p from User u inner join u.userProj p where u.id = :id" )
	public List<Project> findProjectsByUserId(@Param("id") Long id);
	
	@Query(value="select r from User u inner join u.userRoles r where u.id = :id" )
	public List<Role> findRolesByUserId(@Param("id") Long id);
	
	@Query(value = "select um.meeting from User u inner join u.userMeeting um where u.id = :id")
	public List<Meeting> findMeetingByUserId(@Param("id") Long id);
	
	@Query(value = "select u from User u join u.userProj up join u.userDept ud where up.id = :projId and ud.id = :deptId")
	public List<User> findUserByProjectIdAndDeptmentId(@Param("deptId") Long deptId , @Param("projId") Long projId);
	
	@Query(value = "select u from User u join u.userDept ud where ud.id = :deptId")
	public List<User> findUserByDeptmentId(@Param("deptId") Long deptId);
	
	@Query(value = "select u from User u join u.userProj up where up.id = :projId ")
	public List<User> findUserByProjectId(Long projId);
	
	@Query(value = "select m from Meeting m inner join m.meetingTaker mt where mt.id = :id")
	public List<Meeting> findMeetingByMeetingTkr(@Param("id") Long id);

}