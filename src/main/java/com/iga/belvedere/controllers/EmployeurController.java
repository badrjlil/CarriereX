package com.iga.belvedere.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.iga.belvedere.entities.Application;
import com.iga.belvedere.entities.Catégorie;
import com.iga.belvedere.entities.Cours;
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
import com.iga.belvedere.repositories.coursRepository;
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
	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private coursRepository coursRepo;

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
			Employeur emp = employeurRepo.getById((int) session.getAttribute("empId"));
			model.addAttribute("emp", emp);
			return "dashboard/index";
		} else {
			return "redirect:/employeurLogin";
		}

	}

	@GetMapping("/postJob")
	public String Ajoutjob(Model model, HttpSession session) {
		if (verifyLogin(session)) {
			Employeur emp = employeurRepo.getById((int) session.getAttribute("empId"));
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
			@ModelAttribute("newEmploi") Emploi emploi, @RequestParam String keywordsInput,
			@RequestParam MultipartFile image, HttpSession session) {
		if (verifyLogin(session)) {
			Employeur employeur = employeurRepo.getById((int) session.getAttribute("empId"));
			emploi.setEmployeur(employeur);
			emploi.setCatégorie(catégorie);
			emploi.setLangue(langue);
			emploi.setVille(ville);
			try {
				emploi.setImageData(image.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			String[] keywords = keywordsInput.split(",");
			for (String keywordStr : keywords) {
				Keyword keyword = new Keyword(keywordStr.trim());
				keyword.setEmploi(emploi);
				keywordRepo.save(keyword);
			}
			emploiRepo.save(emploi);

			return "redirect:/gererEmplois";
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
			Employeur emp = employeurRepo.getById((int) session.getAttribute("empId"));
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
			Employeur emp = employeurRepo.getById((int) session.getAttribute("empId"));
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
			@RequestParam String description, @RequestParam(required = false) MultipartFile image,
			HttpSession session) {
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

			if (image.getSize() != 0) {
				try {
					emploi.setImageData(image.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

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
			Employeur emp = employeurRepo.getById((int) session.getAttribute("empId"));
			model.addAttribute("emp", emp);
			//List<Application> apps = applicationRepo.findAll();
			List<Application> apps = applicationRepo.findAllByEmployeur(emp);
			/*HashMap<Ingenieur, Application> uniqueApps = new HashMap<>();
			for (Application app : apps) {
				Ingenieur engineer = app.getIngenieur();
				if (!uniqueApps.containsKey(engineer)) {
					uniqueApps.put(engineer, app);
				}
			}
			List<Application> filteredApps = new ArrayList<>(uniqueApps.values());
			apps.clear();
			apps.addAll(filteredApps);*/
			model.addAttribute("apps", apps);
			return "dashboard/manage-candidates";
		} else {
			return "redirect:/employeurLogin";
		}
	}

	@GetMapping("/candidats")
	public String candidats(@RequestParam int id, Model model, HttpSession session) {
		if (verifyLogin(session)) {
			Employeur emp = employeurRepo.getById((int) session.getAttribute("empId"));
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
			Employeur emp = employeurRepo.getById((int) session.getAttribute("empId"));
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
			Employeur emp = employeurRepo.getById((int) session.getAttribute("empId"));
			model.addAttribute("emp", emp);
			model.addAttribute("catégories", catégorieRepo.findAll());
			model.addAttribute("ville", villeRepo.findAll());
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
			session.setAttribute("empId", emp.getId());
			session.setAttribute("empFullName", emp.getPrenom() + " " + emp.getNom());
			return "redirect:/dashboard";
		} else {
			return "redirect:/employeurLogin";
		}
	}

	@GetMapping("/employeurLogout")
	public String employeurLogout(HttpSession session) {
		session.removeAttribute("empId");
		return "redirect:/employeurLogin";
	}

	@GetMapping("changePass")
	public String changePass(HttpSession session, Model model) {
		if (verifyLogin(session)) {
			Employeur emp = employeurRepo.getById((int) session.getAttribute("empId"));
			model.addAttribute("emp", emp);
			return "dashboard/change-password";
		} else {
			return "redirect:/employeurLogin";
		}

	}

	@PostMapping("saveNewPass2")
	public String saveNewPass2(HttpSession session, @RequestParam String currentPass, @RequestParam String newPass,
			@RequestParam String confirmPass) {
		if (verifyLogin(session)) {
			Employeur emp = employeurRepo.getById((int) session.getAttribute("empId"));
			if (emp.getPassword().equals(currentPass)) {
				emp.setPassword(newPass);
				employeurRepo.save(emp);
				return "redirect:/dashboard";
			}
			return "redirect:/changePass";

		} else {
			return "redirect:/employeurLogin";
		}

	}

	@PostMapping("/newEmployeur")
	public String newEmployeur(@RequestParam String prenom, @RequestParam String nom, @RequestParam String email,
			@RequestParam String password) {
		Employeur emp = new Employeur();
		emp.setPrenom(prenom);
		emp.setNom(nom);
		emp.setEmail(email);
		emp.setPassword(password);
		try {
			Resource resource = resourceLoader.getResource("classpath:static/assets/img/profile-icon.png");
			byte[] imageBytes = Files.readAllBytes(Path.of(resource.getURI()));
			emp.setImage(imageBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		employeurRepo.save(emp);
		return "redirect:/employeurLogin";
	}

	@GetMapping("/downloadCV")
	public ResponseEntity<Resource> downloadFile(@RequestParam int id) throws IOException {
		Application app = applicationRepo.getById(id);
		byte[] cv = app.getCv();
		ByteArrayResource resource = new ByteArrayResource(cv);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"CV.pdf\"") // Set filename
				.body(resource);

	}

	@GetMapping("/newCours")
	public String newCourse(HttpSession session, Model model) {
		if (verifyLogin(session)) {
			Employeur emp = employeurRepo.getById((int) session.getAttribute("empId"));
			model.addAttribute("emp", emp);
			return "dashboard/post-cours";
		} else {
			return "redirect:/employeurLogin";
		}
	}

	@PostMapping("/addCours")
	public String addCours(HttpSession session, @RequestParam String titre, @RequestParam String contenu,
			@RequestParam MultipartFile image) {
		if (verifyLogin(session)) {
			Employeur emp = employeurRepo.getById((int) session.getAttribute("empId"));
			Cours c = new Cours();
			c.setTitre(titre);
			c.setContenu(contenu);
			c.setDate(Date.valueOf(LocalDate.now()));
			c.setEmployeur(emp);
			try {
				c.setImage(image.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			coursRepo.save(c);
			return "redirect:/newCours";
		} else {
			return "redirect:/employeurLogin";
		}
	}

	@GetMapping("/manageCours")
	public String manageCours(HttpSession session, Model model) {
		if (verifyLogin(session)) {
			Employeur emp = employeurRepo.getById((int) session.getAttribute("empId"));
			model.addAttribute("emp", emp);
			List<Cours> cours = coursRepo.findAllByEmployeur(emp);
			model.addAttribute("cours", cours);
			return "dashboard/manage-cours";
		} else {
			return "redirect:/employeurLogin";
		}
		
	}
	
	@GetMapping("/modifyCours")
	public String modifyCours(HttpSession session, @RequestParam int id, Model model) {
		if (verifyLogin(session)) {
			Employeur emp = employeurRepo.getById((int) session.getAttribute("empId"));
			Cours c = coursRepo.getById(id);
			model.addAttribute("c", c);
			model.addAttribute("emp", emp);
			return "dashboard/modify-cours";
		} else {
			return "redirect:/employeurLogin";
		}
		
	}
	
	@PostMapping("/saveModifCours")
	public String saveModifCours(HttpSession session, @RequestParam(required = false) MultipartFile image,
			@RequestParam String contenu, @RequestParam int id, @RequestParam String titre) {
		if (verifyLogin(session)) {
			Employeur emp = employeurRepo.getById((int) session.getAttribute("empId"));
			Cours c = coursRepo.getById(id);
			c.setContenu(contenu);
			c.setTitre(titre);
			if (image.getSize() != 0) {
				try {
					c.setImage(image.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			coursRepo.save(c);
			return "redirect:/manageCours";
		} else {
			return "redirect:/employeurLogin";
		}		
	}
	
	@GetMapping("/deleteCours")
	public String deleteCours(HttpSession session, @RequestParam int id) {
		if (verifyLogin(session)) {
			Employeur emp = employeurRepo.getById((int) session.getAttribute("empId"));
			coursRepo.delete(coursRepo.getById(id));
			return "redirect:/manageCours";
		} else {
			return "redirect:/employeurLogin";
		}
	}

}
