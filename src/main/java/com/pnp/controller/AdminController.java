package com.pnp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/admin/create-mom")
	public String viewMOM() {
		return "admin/create-mom";
	}

}
