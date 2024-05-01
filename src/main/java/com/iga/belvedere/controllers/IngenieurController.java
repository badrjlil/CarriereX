package com.iga.belvedere.controllers;

import java.sql.Date;
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
import com.iga.belvedere.entities.Expérience;
import com.iga.belvedere.entities.Formation;
import com.iga.belvedere.entities.Ingenieur;
import com.iga.belvedere.entities.Profil;
import com.iga.belvedere.entities.Ville;
import com.iga.belvedere.repositories.categorieRepository;
import com.iga.belvedere.repositories.emploiRepository;
import com.iga.belvedere.repositories.expérienceRepository;
import com.iga.belvedere.repositories.formationRepository;
import com.iga.belvedere.repositories.ingenieurRepository;
import com.iga.belvedere.repositories.profilRepository;
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
	@Autowired
	private formationRepository formationRepo;
	@Autowired
	private profilRepository profilRepo;
	@Autowired
	private expérienceRepository expérienceRepo;

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
		return "redirect:/";
	}
	
	@PostMapping("/saveBasicInfo")
	public String saveBasicInfo(HttpSession session, @RequestParam String nom, @RequestParam String prenom, @RequestParam String email,
			@RequestParam String telephone, @RequestParam String titre,@RequestParam int age) {
		if (session.getAttribute("userId") != null) {
			Ingenieur ing = ingenieurRepo.getById((int) session.getAttribute("userId"));
			ing.setNom(nom);
			ing.setPrenom(prenom);
			ing.setEmail(email);
			ing.setTelephone(telephone);
			ing.setTitre(titre);
			ing.setAge(age);
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
	@PostMapping("/saveFormation")
	public String saveFormation(HttpSession session, @RequestParam String niveau, @RequestParam String spécialité,
	                            @RequestParam String institution, @RequestParam Date debut, @RequestParam Date fin) {
	    if (session.getAttribute("userId") != null) {
	        Integer userId = ((Integer) session.getAttribute("userId")).intValue();    
	        Profil profil = profilRepo.findById(userId).orElse(null);
	        if (profil == null) {
	            return "/sign-in";
	        }
	        Formation formation = new Formation();
	        formation.setNiveau(niveau);
	        formation.setSpécialité(spécialité);
	        formation.setInstitution(institution);
	        formation.setDebut(debut);
	        formation.setFin(fin);
	        formation.setProfil(profil);
	        formationRepo.save(formation);

	        return "redirect:/account";
	    } else {
	        return "/sign-in";
	    }
	}

	@PostMapping("/saveExpérience")
	public String saveExpérience(HttpSession session, @RequestParam String titre, @RequestParam String entreprise,
	                            @RequestParam String description, @RequestParam Date debut, @RequestParam Date fin) {
	    if (session.getAttribute("userId") != null) {
	        Integer userId = ((Integer) session.getAttribute("userId")).intValue();    
	        Profil profil = profilRepo.findById(userId).orElse(null);
	        if (profil == null) {
	            return "/sign-in";
	        }
	        Expérience e = new Expérience();
	        e.setTitre(titre);
	        e.setEntreprise(entreprise);
	        e.setDescription(description);
	        e.setDebut(debut);
	        e.setFin(fin);
	        e.setProfil(profil);
	        expérienceRepo.save(e);

	        return "redirect:/account";
	    } else {
	        return "/sign-in";
	    }
	}



	
}
