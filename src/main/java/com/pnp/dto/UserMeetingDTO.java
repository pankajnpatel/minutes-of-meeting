package com.pnp.dto;

public class UserMeetingDTO {
	
	private Long userMeeting;
	private Long attendee;
	private Boolean isPresent;
	private Long department;
	private Long project;
	private String reason;
	
	public Long getUserMeeting() {
		return userMeeting;
	}
	public void setUserMeeting(Long userMeeting) {
		this.userMeeting = userMeeting;
	}
	public Long getAttendee() {
		return attendee;
	}
	public void setAttendee(Long attendee) {
		this.attendee = attendee;
	}
	public Boolean getIsPresent() {
		return isPresent;
	}
	public void setIsPresent(Boolean isPresent) {
		this.isPresent = isPresent;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Long getDepartment() {
		return department;
	}
	public void setDepartment(Long department) {
		this.department = department;
	}
	public Long getProject() {
		return project;
	}
	public void setProject(Long project) {
		this.project = project;
	}
	

}
