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
	
	@GetMapping("/faq")
	public String faq() {
		return "faq";
	}

	@GetMapping("/job-list")
	public String jobList() {
		return "job-list";
	}

	@GetMapping("/contact")
	public String contact() {
		
		return "contact";
	}

	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}
	
	@GetMapping("/testimonial")
	public String testimonial() {		
		return "testimonial";
	}
	
	@GetMapping("/bot")
	public String bot() {
		return "bot";
	}
	

}
