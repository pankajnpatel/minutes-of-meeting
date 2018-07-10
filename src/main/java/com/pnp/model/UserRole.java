package com.pnp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

	
@Entity
@Table(name = "user_roles")
public class UserRole implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    @Column(name = "user_role_id")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")  
    private User user;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id")
    private Role role;
     
    // additional fields
    private boolean activated;
    
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
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
	

