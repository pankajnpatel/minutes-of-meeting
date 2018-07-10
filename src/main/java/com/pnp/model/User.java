package com.pnp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Agus Suhardi on 22-Jun-17.
 */
@Entity
@Table(name = "user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable{

	private static final long serialVersionUID = -5842495551740395062L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
    private Long id;
    
    @Column(name = "username", length = 20 , nullable=false)
    private String username;
    
    @Column(name = "first_name", length = 50 , nullable=false)
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;

    @Column(nullable=false)
    private String password;
    
    @Column(nullable=false)
    private String email;
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "meetingTaker")
    private Set<Meeting> meeting = new HashSet<Meeting>();
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private Set<UserMeeting> userMeeting = new HashSet<UserMeeting>();
    
    @ManyToMany(fetch = FetchType.LAZY,cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})  
    @JoinTable(name="user_roles", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="role_id"))  
    private Set<Role> userRoles = new HashSet<Role>();
    
	@ManyToMany(fetch = FetchType.LAZY,cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})   
    @JoinTable(name="user_departments", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="dept_id"))
    private Set<Department> userDept = new HashSet<Department>();
	
	@ManyToMany(fetch = FetchType.LAZY,cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})  
    @JoinTable(name="user_projects", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="project_id"))  
    private Set<Project> userProj = new HashSet<Project>();
    
    @Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
    

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Meeting> getMeeting() {
		return meeting;
	}

	public void setMeeting(Set<Meeting> meeting) {
		this.meeting = meeting;
	}

	public Set<UserMeeting> getUserMeeting() {
		return userMeeting;
	}

	public void setUserMeeting(Set<UserMeeting> userMeeting) {
		this.userMeeting = userMeeting;
	}

	public Set<Department> getUserDept() {
		return userDept;
	}

	public void setUserDept(Set<Department> userDept) {
		this.userDept = userDept;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Set<Role> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<Role> userRoles) {
		this.userRoles = userRoles;
	}

	public Set<Project> getUserProj() {
		return userProj;
	}

	public void setUserProj(Set<Project> userProj) {
		this.userProj = userProj;
	}

	@PrePersist
	public void beforeUpdate(){
		createdAt = new Date();
		updatedAt = new Date();
	}

	@PreUpdate
	public void beforeAdd(){
		updatedAt = new Date();
	}
    

   
}