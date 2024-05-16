package com.iga.belvedere.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iga.belvedere.entities.Application;
import com.iga.belvedere.entities.Catégorie;
import com.iga.belvedere.entities.Emploi;
import com.iga.belvedere.entities.Employeur;
import com.iga.belvedere.entities.Ingenieur;
import com.iga.belvedere.entities.Keyword;
import com.iga.belvedere.entities.Langue;
import com.iga.belvedere.entities.Profil;
import com.iga.belvedere.entities.Ville;
import com.iga.belvedere.repositories.LangueRepository;
import com.iga.belvedere.repositories.applicationRepository;
import com.iga.belvedere.repositories.categorieRepository;
import com.iga.belvedere.repositories.emploiRepository;
import com.iga.belvedere.repositories.employeurRepository;
import com.iga.belvedere.repositories.ingenieurRepository;
import com.iga.belvedere.repositories.villeRepository;

import jakarta.servlet.http.HttpSession;

import com.iga.belvedere.repositories.keywordRepository;
import com.iga.belvedere.repositories.profilRepository;

@Controller
public class EmployeurController {
	@Autowired
	private employeurRepository employeurRepo;
	@Autowired
	private emploiRepository emploiRepo;
	@Autowired
	private categorieRepository catégorieRepo;
	@Autowired
	private LangueRepository langueRepo;
	@Autowired
	private villeRepository villeRepo;
	@Autowired
	private keywordRepository keywordRepo;
	@Autowired
	private applicationRepository applicationRepo;
	@Autowired
	private ingenieurRepository ingenieurRepo;
	@Autowired
	private profilRepository profilRepo;

	private static boolean verifyLogin(HttpSession session) {
		if (session.getAttribute("empId") != null) {
			return true;
		} else {
			return false;
		}
	}

	@GetMapping("/dashboard")
	public String dashbard(HttpSession session, Model model) {
		if (verifyLogin(session)) {
			Employeur emp = employeurRepo.getById((int)session.getAttribute("empId"));
			model.addAttribute("emp", emp);
			return "dashboard/index";
		} else {
			return "redirect:/employeurLogin";
		}

	}

	@GetMapping("/postJob")
	public String Ajoutjob(Model model, HttpSession session) {
		if (verifyLogin(session)) {
			Employeur emp = employeurRepo.getById((int)session.getAttribute( "empId"));
			model.addAttribute("emp", emp);
			Emploi newEmploi = new Emploi();
			List<Employeur> employeurs = employeurRepo.findAll();
			List<Catégorie> catégories = catégorieRepo.findAll();
			List<Langue> langues = langueRepo.findAll();
			List<Ville> villes = villeRepo.findAll();
			model.addAttribute("newEmploi", newEmploi);
			model.addAttribute("employeurs", employeurs);
			model.addAttribute("catégories", catégories);
			model.addAttribute("langues", langues);
			model.addAttribute("villes", villes);
			
			return "dashboard/post-job";
		} else {
			return "redirect:/employeurLogin";
		}
	}

	@PostMapping("/saveEmploi")

	public String saveEmploi(@RequestParam Catégorie catégorie, @RequestParam Langue langue, @RequestParam Ville ville,
			@ModelAttribute("newEmploi") Emploi emploi, @RequestParam String keywordsInput, HttpSession session) {
		if (verifyLogin(session)) {
			Employeur employeur = employeurRepo.findById(1).orElse(null);
			emploi.setEmployeur(employeur);
			emploi.setCatégorie(catégorie);
			emploi.setLangue(langue);

			emploi.setVille(ville);

			String[] keywords = keywordsInput.split(",");
			for (String keywordStr : keywords) {
				Keyword keyword = new Keyword(keywordStr.trim());
				keyword.setEmploi(emploi);
				keywordRepo.save(keyword);
			}
			emploiRepo.save(emploi);

			return "redirect:/dashboard";
		} else {
			return "redirect:/employeurLogin";
		}

	}

	@GetMapping("/catagories")
	public String catagories(Model model) {
		List<Catégorie> categories = catégorieRepo.findAll();
		model.addAttribute("categories", categories);
		return "catagories";
	}

	@GetMapping("/gererEmplois")
	public String gererEmplois(Model model, HttpSession session) {
		if (verifyLogin(session)) {
			Employeur emp = employeurRepo.getById((int)session.getAttribute( "empId"));
			model.addAttribute("emp", emp);
			List<Emploi> emplois = emploiRepo.findAllByEmployeur(emp);
			int totalApplications = 0;
			for (Emploi e : emplois) {
				totalApplications += e.getApplications().size();
			}
			model.addAttribute("emplois", emplois);
			model.addAttribute("totalApplications", totalApplications);
			return "dashboard/manage-jobs";
		} else {
			return "redirect:/employeurLogin";
		}
	}

	@GetMapping("/modifyJob")
	public String modifyJob(Model model, @RequestParam int id, HttpSession session) {
		if (verifyLogin(session)) {
			Employeur emp = employeurRepo.getById((int)session.getAttribute( "empId"));
			model.addAttribute("emp", emp);
			Emploi empl = emploiRepo.getById(id);
			model.addAttribute("emploi", empl);
			List<Catégorie> catégories = catégorieRepo.findAll();
			List<Langue> langues = langueRepo.findAll();
			List<Ville> villes = villeRepo.findAll();
			List<Keyword> keywords = empl.getKeywords();
			String keywordsString = keywords.stream().map(Keyword::getNom).collect(Collectors.joining(", "));
			model.addAttribute("keywordsString", keywordsString);
			model.addAttribute("catégories", catégories);
			model.addAttribute("langues", langues);
			model.addAttribute("villes", villes);
			return "dashboard/modify-job";
		} else {
			return "redirect:/employeurLogin";
		}
	}

	@PostMapping("/saveModifEmploi")

	public String saveModifEmploi(@RequestParam int id, @RequestParam String titre, @RequestParam String nomEntreprise,
	        @RequestParam String website, @RequestParam String emailEntreprise, @RequestParam String type,
	        @RequestParam Langue langue, @RequestParam Ville ville, @RequestParam Catégorie catégorie,
	        @RequestParam String salaire_min, @RequestParam String salaire_max, @RequestParam int experience,
	        @RequestParam Date deadline, @RequestParam String keywordsInput, @RequestParam String exigences,
	        @RequestParam String description, HttpSession session) {
	    if (verifyLogin(session)) {
	        Emploi emploi = emploiRepo.getById(id);
	        Employeur employeur = employeurRepo.findById(1).orElse(null);
	        emploi.setEmployeur(employeur);
	        emploi.setTitre(titre);
	        emploi.setNomEntreprise(nomEntreprise);
	        emploi.setWebsite(website);
	        emploi.setEmailEntreprise(emailEntreprise);
	        emploi.setType(type);
	        emploi.setSalaire_min(Double.parseDouble(salaire_min));
	        emploi.setSalaire_max(Double.parseDouble(salaire_max));
	        emploi.setExperience(experience);
	        emploi.setDeadline(deadline);
	        emploi.setExigences(exigences);
	        emploi.setDescription(description);

	        emploi.setLangue(langue);
	        emploi.setVille(ville);
	        emploi.setCatégorie(catégorie);

	        
	        List<Keyword> existingKeywords = keywordRepo.findByEmploi(emploi);
	        String[] keywords = keywordsInput.split(",");
	        for (Keyword existingKeyword : existingKeywords) {
	            if (!Arrays.asList(keywords).contains(existingKeyword.getNom())) {
	                keywordRepo.delete(existingKeyword);
	            }
	        }
	        for (String keywordStr : keywords) {
	            String trimmedKeyword = keywordStr.trim();
	            if (existingKeywords.stream().noneMatch(k -> k.getNom().equals(trimmedKeyword))) {
	                Keyword keyword = new Keyword(trimmedKeyword);
	                keyword.setEmploi(emploi);
	                keywordRepo.save(keyword);
	            }
	        }

	        emploiRepo.save(emploi);

	        return "redirect:/gererEmplois";
	    } else {
	        return "redirect:/employeurLogin";
	    }
	}

	@GetMapping("/deleteJob")
	public String deleteJob(@RequestParam int id, HttpSession session) {
		if (verifyLogin(session)) {
			Emploi emp = emploiRepo.getById(id);
			emploiRepo.delete(emp);
			return "redirect:/gererEmplois";
		} else {
			return "redirect:/employeurLogin";
		}
	}

	@GetMapping("/gererCandidats")
	public String manageCandidate(Model model, HttpSession session) {
		if (verifyLogin(session)) {
			Employeur emp = employeurRepo.getById((int)session.getAttribute( "empId"));
			model.addAttribute("emp", emp);
			List<Application> apps = applicationRepo.findAll();
			HashMap<Ingenieur, Application> uniqueApps = new HashMap<>();
			for (Application app : apps) {
				Ingenieur engineer = app.getIngenieur();
				if (!uniqueApps.containsKey(engineer)) {
					uniqueApps.put(engineer, app);
				}
			}
			List<Application> filteredApps = new ArrayList<>(uniqueApps.values());
			apps.clear();
			apps.addAll(filteredApps);
			model.addAttribute("apps", apps);
			return "dashboard/manage-candidates";
		} else {
			return "redirect:/employeurLogin";
		}
	}

	@GetMapping("/candidats")
	public String candidats(@RequestParam int id, Model model, HttpSession session) {
		if (verifyLogin(session)) {
			Employeur emp = employeurRepo.getById((int)session.getAttribute( "empId"));
			model.addAttribute("emp", emp);
			List<Application> apps = applicationRepo.findApplicationsByEmploiId(id);
			model.addAttribute("apps", apps);
			return "dashboard/manage-candidates";
		} else {
			return "redirect:/employeurLogin";
		}
	}

	@GetMapping("/candidatDetails")
	public String candidatDetails(@RequestParam int id, Model model, HttpSession session) {
		if (verifyLogin(session)) {
			Employeur emp = employeurRepo.getById((int)session.getAttribute( "empId"));
			model.addAttribute("emp", emp);
			Ingenieur candidat = ingenieurRepo.getById(id);
			Profil profil = profilRepo.findByIng(candidat);
			model.addAttribute("ing", candidat);
			model.addAttribute("profil", profil);
			return "dashboard/candidate-details";
		} else {
			return "redirect:/employeurLogin";
		}
	}

	@GetMapping("/dashProfile")
	public String dashProfile(HttpSession session, Model model) {
		if (verifyLogin(session)) {
			Employeur emp = employeurRepo.getById((int)session.getAttribute( "empId"));
			model.addAttribute("emp", emp);
			return "dashboard/dashboard-profile";
		} else {
			return "redirect:/employeurLogin";
		}
	}

	@GetMapping("/employeurLogin")
	public String employeurLogin(HttpSession session) {
		if (verifyLogin(session)) {
			return "redirect:/dashboard";
		} else {
			return "dashboard/login-signup";
		}

	}

	@PostMapping("/employeurLogin")
	public String employeurLogin2(@RequestParam String email, @RequestParam String password, HttpSession session) {
		Employeur emp = employeurRepo.fetchUser(email, password);
		if (emp != null) {
			System.out.println("found");
			session.setAttribute("empId", emp.getId());
			session.setAttribute("empFullName", emp.getPrenom() + " " + emp.getNom());
			return "redirect:/dashboard";
		} else {
			System.out.println("not found");
			return "redirect:/employeurLogin";
		}
	}

	@GetMapping("/employeurLogout")
	public String employeurLogout(HttpSession session) {
		session.removeAttribute("empId");
		return "redirect:/employeurLogin";
	}
	
	@GetMapping("/changePassword")
	public String changePassword() {
		
		return "dashboard/change-password";
	}

}
