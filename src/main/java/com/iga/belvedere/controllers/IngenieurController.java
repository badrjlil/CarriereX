package com.iga.belvedere.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iga.belvedere.entities.Catégorie;
import com.iga.belvedere.entities.Compétence;
import com.iga.belvedere.entities.Emploi;
import com.iga.belvedere.entities.Expérience;
import com.iga.belvedere.entities.Formation;
import com.iga.belvedere.entities.Ingenieur;
import com.iga.belvedere.entities.Langue;
import com.iga.belvedere.entities.Profil;
import com.iga.belvedere.entities.Ville;
import com.iga.belvedere.repositories.LangueRepository;
import com.iga.belvedere.repositories.categorieRepository;
import com.iga.belvedere.repositories.compétenceRepository;
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
	@Autowired
	private compétenceRepository compétenceRepo;
	@Autowired
	private LangueRepository langueRepo;
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
		return "redirect:/account";
	}

	@GetMapping("/account")
	public String account(Model model, HttpSession session) {
	    if (session.getAttribute("userId") != null) {
	        Ingenieur ing = ingenieurRepo.getById((int) session.getAttribute("userId"));
	        List<Compétence> competences = ing.getProfil().getCompétences();
	        String competencesString = competences.stream().map(Compétence::getNom).collect(Collectors.joining(","));
	        model.addAttribute("ing", ing);
	        model.addAttribute("competencesString", competencesString);
	        
	        List<Langue> langue = ing.getProfil().getLangues();
	        String languesString = langue.stream().map(Langue::getNom).collect(Collectors.joining(","));
	        model.addAttribute("languesString", languesString);
	        
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
	        @RequestParam String telephone, @RequestParam String titre, @RequestParam int age, @RequestParam String competences, @RequestParam String langues) {
	    if (session.getAttribute("userId") != null) {
	        Ingenieur ing = ingenieurRepo.getById((int) session.getAttribute("userId"));
	        ing.setNom(nom);
	        ing.setPrenom(prenom);
	        ing.setEmail(email);
	        ing.setTelephone(telephone);
	        ing.setTitre(titre);
	        ing.setAge(age);
	        ingenieurRepo.save(ing);

	        
	        Profil profil = ing.getProfil();
	        if (profil == null) {
	        	return "/sign-in";
	        }

	        List<Compétence> existingCompetences = profil.getCompétences();
	        String[] newCompetenceArray = competences.split(",");
	        
	        // kimseh man la base de données dakchi li n9asnah
	        for (Compétence existingCompetence : existingCompetences) {
	            if (!Arrays.asList(newCompetenceArray).contains(existingCompetence.getNom())) {
	                compétenceRepo.delete(existingCompetence);
	            }
	        }

	        //kizid f la base de données dakchi li makayench
	        for (String competenceName : newCompetenceArray) {
	            String CompetenceName = competenceName.trim();
	            if (existingCompetences.stream().noneMatch(c -> c.getNom().equals(CompetenceName))) {
	                Compétence newCompetence = new Compétence();
	                newCompetence.setNom(CompetenceName);
	                newCompetence.setProfil(profil);
	                compétenceRepo.save(newCompetence);
	            }
	        }
	        
	        List<Langue> existingLangue = profil.getLangues();
	        String[] newLangueArray = langues.split(",");
	        
	        
	        for (Langue existingLangues : existingLangue) {
	            if (!Arrays.asList(newLangueArray).contains(existingLangues.getNom())) {
	            	langueRepo.delete(existingLangues);
	            }
	        }
	        
	        for (String LangueNom : newLangueArray) {
	            String LangueName = LangueNom.trim();
	            if (existingLangue.stream().noneMatch(c -> c.getNom().equals(LangueName))) {
	                Langue newLangue = new Langue();
	                newLangue.setNom(LangueName);
	                newLangue.setProfil(profil);
	                langueRepo.save(newLangue);
	            }
	        }

	        return "redirect:/account";
	    } else {
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
