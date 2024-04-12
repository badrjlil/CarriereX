package com.iga.belvedere.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class myController {
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("about")
	public String about() {
		
		return "about";
	}
	
	@GetMapping("job-list")
	public String jobList() {
		
		return "job-list";
	}
	
	@GetMapping("job-detail")
	public String jobDetail() {
		
		return "job-detail";
	}
	
	@GetMapping("contact")
	public String contact() {
		
		return "contact";
	}
	
	@GetMapping("category")
	public String catégorie() {
		
		return "category";
	}
	
	@GetMapping("testimonial")
	public String testimonial() {
		
		return "testimonial";
	}
	
	@GetMapping("404")
	public String error404() {
		
		return "404";
	}
}
