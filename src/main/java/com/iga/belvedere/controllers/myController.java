package com.iga.belvedere.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.iga.belvedere.entities.Catégorie;
import com.iga.belvedere.entities.Emploi;
import com.iga.belvedere.entities.Ville;
import com.iga.belvedere.repositories.categorieRepository;
import com.iga.belvedere.repositories.emploiRepository;
import com.iga.belvedere.repositories.employeurRepository;
import com.iga.belvedere.repositories.villeRepository;


@Controller
public class myController {
	@Autowired
	private emploiRepository emploiRepo;
	@Autowired
	private categorieRepository catégorieRepo;
	@Autowired
	private villeRepository villeRepo;

	@GetMapping("/")
	public String home(Model model) {
		List<Ville> villes = villeRepo.findAll();
		model.addAttribute("villes", villes);
		List<Catégorie> categories = catégorieRepo.findAll();
		model.addAttribute("categories", categories);
		List<Emploi> emplois = emploiRepo.findAll();
		model.addAttribute("emplois", emplois);
		return "index";
	}
	
	@GetMapping("/about")
	public String about() {
		
		return "about";
	}
	@GetMapping("/blog")
	public String blog() {
		
		return "blog";
	}
	@GetMapping("/blog-details")
	public String blogdetails() {
		
		return "blog-details";
	}
	@GetMapping("/blog-two")
	public String blogtwo() {
		
		return "blog-two";
	}
	@GetMapping("/candidate")
	public String candidate() {
		
		return "candidate";
	}
	@GetMapping("/candidate-details")
	public String candidatedetails() {
		
		return "candidate-details";
	}
	
	@GetMapping("/company")
	public String company() {
		
		return "company";
	}
	@GetMapping("/faq")
	public String faq() {
		
		return "faq";
	}

	@GetMapping("/job-list")
	public String jobList() {
		
		return "job-list";
	}
	
	@GetMapping("/job-grid")
	public String jobgrid() {
		return "job-grid";
	}
	
	@GetMapping("/contact")
	public String contact() {
		
		return "contact";
	}

	@GetMapping("/pricing")
	public String pricing() {
		return "pricing";
	}
	@GetMapping("/privacy")
	public String privacy() {
		return "privacy";
	}
	@GetMapping("/privacy-policy")
	public String privacypolicy() {
		return "privacy-policy";
	}
	@GetMapping("/reset-password")
	public String password() {
		return "reset-password";
	}
	@GetMapping("/resume")
	public String resume() {
		return "resume";
	}
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}
	@GetMapping("/terms")
	public String terms() {
		return "terms";
	}
	@GetMapping("/terms-condition")
	public String termscondition() {
		return "terms-condition";
	}
	@GetMapping("/testimonial")
	public String testimonial() {
		
		return "testimonial";
	}
	
	@GetMapping("/404")
	public String error404() {
		
		return "404";
	}
}
