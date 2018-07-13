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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="meeting")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Meeting  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7610713946354410516L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="meeting_id")
	private Long id;
	
	private String title;
	
	@Column(length=2000)
	private String agenda;
	
	private String location;
	
	@ManyToOne(fetch=FetchType.LAZY ,cascade = {CascadeType.MERGE} )
	@JoinColumn(name="user_id", nullable=false)
	private User meetingTaker;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private Set<UserMeeting> userMeetingSet = new HashSet<UserMeeting>();
	
	@ManyToMany(fetch = FetchType.LAZY,cascade={CascadeType.MERGE})  
    @JoinTable(name="meeting_dept", joinColumns=@JoinColumn(name="meeting_id"), inverseJoinColumns=@JoinColumn(name="dept_id"))  
    private Set<Department> meetingDept = new HashSet<Department>();
	
	@ManyToMany(fetch = FetchType.LAZY,cascade={CascadeType.MERGE})  
    @JoinTable(name="meeting_proj", joinColumns=@JoinColumn(name="meeting_id"), inverseJoinColumns=@JoinColumn(name="project_id"))  
    private Set<Project> meetingProj = new HashSet<Project>();

	@Column(name = "from_dt",nullable = false)
	@Temporal(TemporalType.DATE) 
	private Date fromDate;
	
	@Column(name = "to_dt",nullable = false)
	@Temporal(TemporalType.DATE)
	private Date toDate;

    @Temporal(TemporalType.TIME)
    @Column(name = "start_time")
	private Date fromTime;
	
    @Temporal(TemporalType.TIME)
    @Column(name = "end_time")
	private Date toTime;
	
	
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

	public String getAgenda() {
		return agenda;
	}

	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public User getMeetingTaker() {
		return meetingTaker;
	}

	public void setMeetingTaker(User meetingTaker) {
		this.meetingTaker = meetingTaker;
	}
	
	public Set<UserMeeting> getUserMeetingSet() {
		return userMeetingSet;
	}

	public void setUserMeetingSet(Set<UserMeeting> userMeetingSet) {
		this.userMeetingSet = userMeetingSet;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getFromTime() {
		return fromTime;
	}

	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}

	public Date getToTime() {
		return toTime;
	}

	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}

	public Set<Department> getMeetingDept() {
		return meetingDept;
	}

	public void setMeetingDept(Set<Department> meetingDept) {
		this.meetingDept = meetingDept;
	}

	public Set<Project> getMeetingProj() {
		return meetingProj;
	}

	public void setMeetingProj(Set<Project> meetingProj) {
		this.meetingProj = meetingProj;
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
