package com.pnp.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MeetingDTO {
	
	 private Long id;
	 private String title;
	 private String location;
	 private Long meetingTaker;

	 private Date fromDate;
	 private Date toDate; 
	 
	 @JsonFormat(pattern="HH:mm")
	 private Date fromTime;
	 @JsonFormat(pattern="HH:mm")
	 private Date toTime;
	 private String agenda;
	 private List<UserMeetingDTO> userMeeting;
     
     
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Long getMeetingTaker() {
		return meetingTaker;
	}
	public void setMeetingTaker(Long meetingTaker) {
		this.meetingTaker = meetingTaker;
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
	public String getAgenda() {
		return agenda;
	}
	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}
	public List<UserMeetingDTO> getUserMeeting() {
		return userMeeting;
	}
	public void setUserMeeting(List<UserMeetingDTO> userMeeting) {
		this.userMeeting = userMeeting;
	}
	

}
