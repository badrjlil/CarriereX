package com.iga.belvedere.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.iga.belvedere.entities.Catégorie;
import com.iga.belvedere.entities.Emploi;
import com.iga.belvedere.entities.Ville;
import com.iga.belvedere.repositories.categorieRepository;
import com.iga.belvedere.repositories.emploiRepository;
import com.iga.belvedere.repositories.villeRepository;

@Controller
public class IngenieurController {

	@Autowired
	private emploiRepository repoEmploi;
	@Autowired
	private categorieRepository repoCatégorie;
	@Autowired
	private villeRepository villeRepo;

	@GetMapping("/find-job")
	public String findjob(Model model) {
		List<Ville> villes = villeRepo.findAll();
		model.addAttribute("villes", villes);
		List<Catégorie> catégories = repoCatégorie.findAll();
		model.addAttribute("catégories", catégories);
		return "find-job";
	}

	@GetMapping("/findJob")
	public String findJob(@RequestParam String keyword, @RequestParam(required = false) Integer ville,
			@RequestParam(required = false) Integer category, Model model) {
		System.out.println("keyword: " + keyword);
		System.out.println("location: " + ville);
		System.out.println("category: " + category);
		LocalDate date = LocalDate.now();
	    model.addAttribute("date", date);

	    if (category != null) {
	        List<Emploi> emplois = repoEmploi.findAllByKeyword(keyword, ville, category);
	        model.addAttribute("emplois", emplois);
	    } else {
	        List<Emploi> emplois = repoEmploi.findAllByKeyword(keyword, ville);
	        model.addAttribute("emplois", emplois);
	    }
		return "job-list";
	}


	@GetMapping("/job-details")
	public String jobDetails(@RequestParam int id, Model model) {
		Emploi emploi = repoEmploi.getById(id);
		model.addAttribute("emploi", emploi);
		return "job-details";
	}
	
	@GetMapping("/add-categorie")
	public String addCat() {
		return "add-categorie";
	}

}
