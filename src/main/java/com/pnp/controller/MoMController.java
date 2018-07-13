package com.pnp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pnp.dao.MeetingRepository;
import com.pnp.model.Meeting;

@Controller
public class MoMController {
	
	@Autowired
	MeetingRepository meetingRepo;
	
	@GetMapping("/mom/update/{mom-id}")
	public String viewMOM(@PathVariable(name="mom-id") String momId, Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	boolean isAdmin = authentication.getAuthorities().stream().anyMatch( auth -> {
    		return auth.getAuthority().trim().equals("SUPER_ADMIN") || auth.getAuthority().trim().equals("TEAM_ADMIN");
    	});
    	
    	Meeting meeting = meetingRepo.getOne(Long.valueOf(momId));
		
		if (meeting != null) {
			model.addAttribute("IS_ADMIN", isAdmin);
			model.addAttribute("meetingId", momId);

			return "admin/create-mom";

		} else {

			return isAdmin ? "admin/admin-dashboard" : "user/user-dashboard";
		}
	}
	
}
