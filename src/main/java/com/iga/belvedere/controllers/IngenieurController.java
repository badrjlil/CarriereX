package com.iga.belvedere.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iga.belvedere.entities.Catégorie;
import com.iga.belvedere.entities.Emploi;
import com.iga.belvedere.entities.Ingenieur;
import com.iga.belvedere.entities.Ville;
import com.iga.belvedere.repositories.categorieRepository;
import com.iga.belvedere.repositories.emploiRepository;
import com.iga.belvedere.repositories.ingenieurRepository;
import com.iga.belvedere.repositories.villeRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class IngenieurController {

	@Autowired
	private emploiRepository repoEmploi;
	@Autowired
	private categorieRepository repoCatégorie;
	@Autowired
	private villeRepository villeRepo;
	@Autowired
	private ingenieurRepository ingenieurRepo;

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

	@PostMapping("/account")
	public String sign_in(HttpSession session, @RequestParam String email, @RequestParam String password, Model model) {
		Ingenieur ing = ingenieurRepo.fetchUser(email, password);
		session.setAttribute("userId", ing.getId());
		model.addAttribute("ing", ing);
		return "account";
	}

	@GetMapping("/account")
	public String account(Model model, HttpSession session) {
		if (session.getAttribute("userId") != null) {
			Ingenieur ing = ingenieurRepo.getById((int) session.getAttribute("userId"));
			model.addAttribute("ing", ing);
			return "account";
		} else {
			return "/sign-in";
		}
	}

	@GetMapping("/sign-out")
	public String sign_out(HttpSession session) {
		session.invalidate();
		return "redirect:/account";
	}
	
	@PostMapping("/saveBasicInfo")
	public String saveBasicInfo(HttpSession session, @RequestParam String nom, @RequestParam String prenom, @RequestParam String email,
			@RequestParam String telephone, @RequestParam String titre) {
		if (session.getAttribute("userId") != null) {
			Ingenieur ing = ingenieurRepo.getById((int) session.getAttribute("userId"));
			ing.setNom(nom);
			ing.setPrenom(prenom);
			ing.setEmail(email);
			ing.setTelephone(telephone);
			ing.setTitre(titre);
			ingenieurRepo.save(ing);
			return "redirect:/account";
		}else {
			return "/sign-in";
		}
	}
	
	@PostMapping("/saveAddress")
	public String saveAddress(HttpSession session, @RequestParam String pays, @RequestParam String ville, @RequestParam String region) {
		if (session.getAttribute("userId") != null) {
			Ingenieur ing = ingenieurRepo.getById((int) session.getAttribute("userId"));
			ing.setPays(pays);
			ing.setVille(ville);
			ing.setRegion(region);
			ingenieurRepo.save(ing);
			return "redirect:/account";
		}else {
			return "/sign-in";
		}
	}
	
	@GetMapping("/sign-up")
	public String sign_up(HttpSession session) {
		if (session.getAttribute("userId") != null) {
			return "redirect:/account";
		}else {
			return "sign-up";
		}
	}
	
	@PostMapping("/sign-up")
	public String sign_up(@RequestParam String email, @RequestParam String password,
			@RequestParam String prenom, @RequestParam String nom) {
		Ingenieur ing = new Ingenieur();
		ing.setPrenom(prenom);
		ing.setNom(nom);
		ing.setEmail(email);
		ing.setPassword(password);
		ingenieurRepo.save(ing);
		return "redirect:/account";
	}

	
}
