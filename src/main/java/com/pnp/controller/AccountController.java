package com.pnp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pnp.dao.UserRepository;
import com.pnp.model.User;

@Controller
public class AccountController {

	@Autowired
	UserRepository userRepo;
	
    @PostMapping("/login-success")
    public String loginSuccess(ModelMap model) {
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	boolean isAdmin = authentication.getAuthorities().stream().anyMatch( auth -> {
    		return auth.getAuthority().trim().equals("SUPER_ADMIN") || auth.getAuthority().trim().equals("TEAM_ADMIN");
    	});
        return isAdmin? "admin/admin-dashboard" : "user/user-dashboard";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(ModelMap model) {
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	boolean isAdmin = authentication.getAuthorities().stream().anyMatch( auth -> {
    		return auth.getAuthority().trim().equals("SUPER_ADMIN") || auth.getAuthority().trim().equals("TEAM_ADMIN");
    	});
    	
    	if(isAdmin){
    		List<User> userList = userRepo.findAll();
			userList.get(0).getUserRoles().toArray();
    		model.addAttribute("user_list", userList);
    	}
    	
        return isAdmin? "admin/admin-dashboard" : "user/user-dashboard";
    }
    
    @GetMapping("/login")
    public String login(ModelMap model) {
    	
    	if ( SecurityContextHolder.getContext().getAuthentication() != null &&
    			 SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
    			 !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) ){
    		return dashboard(model);
    	}
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
