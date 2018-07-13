package com.pnp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pnp.dao.MeetingRepository;
import com.pnp.dao.UserRepository;
import com.pnp.dto.MeetingDTO;
import com.pnp.model.Meeting;
import com.pnp.model.User;
import com.pnp.service.MeetingService;

@RestController
public class MeetingController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	MeetingRepository meetingRepo;
	
	@Autowired
	MeetingService meetingService;
	
	@PostMapping("/meeting/custom/add")
	public ResponseEntity<Meeting> addCustomMeeting(@RequestBody MeetingDTO meetingDTO){
		Meeting meeting = meetingService.saveOrUpdate(meetingDTO);
		return  new ResponseEntity<Meeting>(meeting,HttpStatus.CREATED);
	}
	
	@PostMapping("/meeting/add")
	public ResponseEntity<Meeting> addMeeting(@RequestBody Meeting meeting){
		
		final Meeting m1 = meeting;
		meeting.getUserMeetingSet().forEach( (v) -> {
			v.setMeeting(m1);
		});
		
		meeting = meetingRepo.save(m1);
		return  new ResponseEntity<Meeting>(meeting,HttpStatus.CREATED);
	}
	
	@GetMapping("/meeting/all")
	public ResponseEntity<List<Meeting>> getAllMeetings(){
		List<Meeting> meetingList = meetingRepo.findAll();
		return  new ResponseEntity<List<Meeting>>(meetingList,HttpStatus.OK);
	}
	
	@GetMapping("/meeting/{id}")
	public ResponseEntity<Meeting> getSpecificMeeting(@PathVariable("id") Long id){
		Meeting meeting = meetingRepo.getOne(id);
		
		return  new ResponseEntity<Meeting>(meeting,HttpStatus.OK);
	}
	
	@GetMapping("/meeting/{id}/attendees")
	public ResponseEntity<List<User>> getAllAttendee(@PathVariable("id") Long id){
		List<User> users = meetingRepo.findUserFromMeeting(id);
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	@PutMapping("/meeting/update")
	public ResponseEntity<Meeting> udateMeeting(@RequestBody Meeting meeting){
		
		final Meeting m1 = meeting;
		meeting.getUserMeetingSet().forEach( (v) -> {
			v.setMeeting(m1);
		});
		
		meeting = meetingRepo.save(m1);
		return new ResponseEntity<Meeting>(meeting,HttpStatus.OK);
	}
	
	@DeleteMapping("/meeting/del/{id}")
	public ResponseEntity<String> deleteMeeting(@PathVariable("id") Long id){
		meetingRepo.deleteById(id);
		return new ResponseEntity<String>("Meeting deleted successfully",HttpStatus.OK);
	}

}
