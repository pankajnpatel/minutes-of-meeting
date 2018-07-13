package com.pnp.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pnp.dao.MeetingRepository;
import com.pnp.dto.MeetingDTO;
import com.pnp.model.Department;
import com.pnp.model.Meeting;
import com.pnp.model.Project;
import com.pnp.model.User;
import com.pnp.model.UserMeeting;

@Service
public class MeetingService {
	
	@Autowired
	MeetingRepository meetingRepo;
	
	public Meeting saveOrUpdate(MeetingDTO meetingDTO){
		
		Meeting meeting = new Meeting();
		meeting.setMeetingTaker(new User(meetingDTO.getMeetingTaker()));
		BeanUtils.copyProperties(meetingDTO, meeting);
		
		Set<UserMeeting> umSet = new HashSet<>();
		Set<Department> deptSet = new HashSet<>();
		Set<Project> projSet = new HashSet<>();
		
		final Meeting m1 = meeting;
		meetingDTO.getUserMeeting().forEach((v) -> {
			UserMeeting  um = new UserMeeting();
			um.setUser(new User(v.getAttendee()));
			um.setMeeting(m1);
			um.setIsPresent(v.getIsPresent());
			um.setmDate(new Date());
			deptSet.add(new Department(v.getDepartment()));
			projSet.add(new Project(v.getProject()));
			umSet.add(um);
			
		});
		
		meeting.setUserMeetingSet(umSet);
		meeting.setMeetingDept(deptSet);
		meeting.setMeetingProj(projSet);
		meeting = meetingRepo.save(meeting);
		

		return meeting;
	}
	
}
