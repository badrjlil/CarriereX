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
import com.iga.belvedere.repositories.categorieRepository;
import com.iga.belvedere.repositories.emploiRepository;

@Controller
public class IngenieurController {

	@Autowired
	private emploiRepository repoEmploi;
	@Autowired
	private categorieRepository repoCatégorie;

	@GetMapping("find-job.html")
	public String findjob(Model model) {
		List<Catégorie> catégories = repoCatégorie.findAll();
		model.addAttribute("catégories", catégories);
		return "find-job";
	}
	
	@GetMapping("/")
	public String home(Model model) {
		List<Catégorie> categories = repoCatégorie.findAll();
		for(Catégorie cat : categories) {
			if(cat.getImageData() != null) {
				System.out.println(cat.getImageData().length);
			}
		}
		model.addAttribute("categories", categories);
		return "index";
	}

	@GetMapping("/findJob")
	public String findJob(@RequestParam String keyword, @RequestParam String location,
			@RequestParam(required = false) Integer category, Model model) {
		LocalDate date = LocalDate.now();
	    model.addAttribute("date", date);

	    if (category != null) {
	        List<Emploi> emplois = repoEmploi.findAllByKeyword(keyword, location, category);
	        model.addAttribute("emplois", emplois);
	    } else {
	        List<Emploi> emplois = repoEmploi.findAllByKeyword(keyword, location);
	        model.addAttribute("emplois", emplois);
	    }
		return "job-list";
	}


	@GetMapping("/jobDetails")
	public String jobDetails(@RequestParam int id, Model model) {
		Emploi emploi = repoEmploi.getById(id);
		model.addAttribute("emploi", emploi);
		return "job-details";
	}
	
	@GetMapping("/add-categorie")
	public String addCat() {
		return "add-categorie";
	}
	
	@PostMapping("/saveCategorie")
	public String saveCat(@RequestParam String nom, @RequestParam("image") MultipartFile image) {
		try {
            Catégorie catégorie = new Catégorie();
            catégorie.setNom(nom);
            catégorie.setImageData(image.getBytes());
            repoCatégorie.save(catégorie);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return "redirect:/add-categorie";
	}

}
