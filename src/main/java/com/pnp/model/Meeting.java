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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	@ManyToOne(fetch=FetchType.LAZY ,cascade = {CascadeType.PERSIST,CascadeType.MERGE} )
	@JoinColumn(name="user_id", nullable=false)
	@JsonProperty(access = Access.WRITE_ONLY)
	private User meetingTaker;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonProperty(access = Access.WRITE_ONLY)
	private Set<UserMeeting> userMeetingSet = new HashSet<UserMeeting>();

	@Column(name = "from_dt",nullable = false)
	@Temporal(TemporalType.DATE) 
	private Date fromDate;
	
	@Column(name = "to_dt",nullable = false)
	@Temporal(TemporalType.DATE)
	private Date toDate;

    @Temporal(TemporalType.TIME)
    @Column(name = "start_time")
	private Date startTime;
	
    @Temporal(TemporalType.TIME)
    @Column(name = "end_time")
	private Date endTime;
	
	
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
