package com.pnp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="user_meetings")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserMeeting implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8334825556394176043L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="meeting_id")
	private Meeting meeting;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	//@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "m_date",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date mDate;
	
	@Column(columnDefinition="TEXT")
	private String prevDesc;
	
	@Column(columnDefinition="TEXT")
	private String currDesc;
	
	@Column(columnDefinition="TEXT")
	private String nextDesc;
	

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

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPrevDesc() {
		return prevDesc;
	}

	public void setPrevDesc(String prevDesc) {
		this.prevDesc = prevDesc;
	}

	public String getCurrDesc() {
		return currDesc;
	}

	public void setCurrDesc(String currDesc) {
		this.currDesc = currDesc;
	}

	public String getNextDesc() {
		return nextDesc;
	}

	public void setNextDesc(String nextDesc) {
		this.nextDesc = nextDesc;
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

	public Date getmDate() {
		return mDate;
	}

	public void setmDate(Date mDate) {
		this.mDate = mDate;
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
