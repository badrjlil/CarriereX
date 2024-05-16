package com.iga.belvedere.controllers;

import java.sql.Date;
import java.text.Normalizer.Form;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.iga.belvedere.entities.Application;
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
import com.iga.belvedere.repositories.applicationRepository;
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
	@Autowired
	private applicationRepository applicationRepo;

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
	public String jobDetails(HttpSession session, @RequestParam int id, Model model) {
		Emploi emploi = repoEmploi.getById(id);
		model.addAttribute("emploi", emploi);
		if (session.getAttribute("userId") != null) {
			Ingenieur ing = ingenieurRepo.getById((int) session.getAttribute("userId"));
			Application app = applicationRepo.findByIngenieurAndEmploi(ing, emploi);

			if (app != null) {
				model.addAttribute("applied", true);
			} else {
				model.addAttribute("applied", false);
			}
		} else {
			model.addAttribute("applied", false);
		}

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
			model.addAttribute("ing", ing);

			Profil profil = profilRepo.findByIng(ing);
			model.addAttribute("profil", profil);

			List<Compétence> competences = ing.getProfil().getCompétences();
			String competencesString = competences.stream().map(Compétence::getNom).collect(Collectors.joining(","));
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
		session.removeAttribute("userId");
		return "redirect:/account";
	}

	@PostMapping("/saveBasicInfo")
	public String saveBasicInfo(HttpSession session, @RequestParam String nom, @RequestParam String prenom,
			@RequestParam String email, @RequestParam String telephone, @RequestParam String titre,
			@RequestParam String Category, @RequestParam int age, @RequestParam int experience,
			@RequestParam String competences, @RequestParam String langues) {
		if (session.getAttribute("userId") != null) {
			Ingenieur ing = ingenieurRepo.getById((int) session.getAttribute("userId"));
			Profil profil = profilRepo.findByIng(ing);
			ing.setNom(nom);
			ing.setPrenom(prenom);
			ing.setEmail(email);
			ing.setTelephone(telephone);
			profil.setTitre(titre);
			profil.setExperience(experience);
			profil.setCategory(Category);
			ing.setAge(age);
			ingenieurRepo.save(ing);

			List<Compétence> existingCompetences = profil.getCompétences();
			String[] newCompetenceArray = competences.split(",");

			// kimseh man la base de données dakchi li n9asnah
			for (Compétence existingCompetence : existingCompetences) {
				if (!Arrays.asList(newCompetenceArray).contains(existingCompetence.getNom())) {
					compétenceRepo.delete(existingCompetence);
				}
			}

			// kizid f la base de données dakchi li makayench
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
	public String saveAddress(HttpSession session, @RequestParam String pays, @RequestParam String ville,
			@RequestParam String region) {
		if (session.getAttribute("userId") != null) {
			Ingenieur ing = ingenieurRepo.getById((int) session.getAttribute("userId"));
			Profil profil = profilRepo.findByIng(ing);
			profil.setPays(pays);
			profil.setVille(ville);
			profil.setRegion(region);
			profilRepo.save(profil);
			return "redirect:/account";
		} else {
			return "/sign-in";
		}
	}

	@PostMapping("/saveFormation")
	public String saveFormation(HttpSession session, @RequestParam String niveau, @RequestParam String description,
			@RequestParam String spécialité, @RequestParam String institution, @RequestParam Date debut,
			@RequestParam Date fin) {
		if (session.getAttribute("userId") != null) {

			Integer userId = ((Integer) session.getAttribute("userId")).intValue();
			Ingenieur ing = ingenieurRepo.getById(userId);
			Profil profil = profilRepo.findByIng(ing);// Id(userId).orElse(null);
			Formation formation = new Formation();
			formation.setNiveau(niveau);
			formation.setSpécialité(spécialité);
			formation.setInstitution(institution);
			formation.setDebut(debut);
			formation.setFin(fin);
			formation.setProfil(profil);
			formation.setDescription(description);
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
			Ingenieur ing = ingenieurRepo.getById(userId);
			Profil profil = profilRepo.findByIng(ing);
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
		} else {
			return "sign-up";
		}
	}

	@PostMapping("/sign-up")
	public String sign_up(@RequestParam String email, @RequestParam String password, @RequestParam String prenom,
			@RequestParam String nom) {
		Ingenieur ing = new Ingenieur();
		ing.setPrenom(prenom);
		ing.setNom(nom);
		ing.setEmail(email);
		ing.setPassword(password);
		// ingenieurRepo.save(ing);
		Profil profil = new Profil();
		profil.setIngenieur(ing);
		ingenieurRepo.save(ing);
		profilRepo.save(profil);
		return "redirect:/account";
	}

	@GetMapping("/formations")
	public String fomrations(Model model, HttpSession session) {
		if (session.getAttribute("userId") != null) {
			Ingenieur ing = ingenieurRepo.getById((int) session.getAttribute("userId"));
			model.addAttribute("ing", ing);

			Profil profil = profilRepo.findByIng(ing);
			model.addAttribute("profil", profil);

			List<Formation> formations = formationRepo.findAllByProfil(ing.getProfil());
			model.addAttribute("formations", formations);
		}
		return "formations";
	}

	@PostMapping("/modifyFormation")
	public String modifyFormation(Model model, @RequestParam int id, @RequestParam Date debut,
			@RequestParam String description, @RequestParam Date fin, @RequestParam String institution,
			@RequestParam String spécialité, @RequestParam String niveau) {
		Formation f = formationRepo.getById(id);
		f.setDebut(debut);
		f.setFin(fin);
		f.setInstitution(institution);
		f.setSpécialité(spécialité);
		f.setNiveau(niveau);
		f.setDescription(description);
		formationRepo.save(f);
		return "redirect:/formations";
	}

	@GetMapping("/deleteFormation")
	public String deleteFormation(@RequestParam int id) {
		formationRepo.deleteById(id);
		return "redirect:/formations";
	}

	@GetMapping("/experiences")
	public String expériences(Model model, HttpSession session) {
		if (session.getAttribute("userId") != null) {
			Ingenieur ing = ingenieurRepo.getById((int) session.getAttribute("userId"));
			model.addAttribute("ing", ing);

			Profil profil = profilRepo.findByIng(ing);
			model.addAttribute("profil", profil);

			List<Expérience> expériences = expérienceRepo.findAllByProfil(ing.getProfil());
			model.addAttribute("expériences", expériences);
		}
		return "experiences";
	}

	@PostMapping("/modifyExperience")
	public String modifyExpérience(Model model, @RequestParam int id, @RequestParam Date debut, @RequestParam Date fin,
			@RequestParam String titre, @RequestParam String entreprise, @RequestParam String description) {
		Expérience e = expérienceRepo.getById(id);
		e.setDebut(debut);
		e.setFin(fin);
		e.setTitre(titre);
		e.setEntreprise(entreprise);
		e.setDescription(description);
		expérienceRepo.save(e);
		return "redirect:/experiences";
	}

	@GetMapping("/deleteExperience")
	public String deleteExpérience(@RequestParam int id) {
		expérienceRepo.deleteById(id);
		return "redirect:/experiences";
	}

	@GetMapping("/resume")
	public String resume(HttpSession session, Model model) {
		if (session.getAttribute("userId") != null) {
			Ingenieur ing = ingenieurRepo.getById((int) session.getAttribute("userId"));
			model.addAttribute("ing", ing);
			Profil p = profilRepo.findByIng(ing);
			model.addAttribute("profil", p);
			List<Formation> formations = formationRepo.findAllByProfil(p);
			model.addAttribute("formations", formations);
			List<Expérience> expériences = expérienceRepo.findAllByProfil(p);
			model.addAttribute("expériences", expériences);
			List<Compétence> compétences = compétenceRepo.findAllByProfil(p);
			model.addAttribute("compétences", compétences);
			return "resume";
		} else {
			return "/sign-in";
		}
	}

	@PostMapping("/submit-application")
	public String submit_application(HttpSession session, @RequestParam int id,
			@RequestParam(required = false) MultipartFile cv,
			@RequestParam(required = false) MultipartFile lettreMotivation) {
		if (session.getAttribute("userId") != null) {
			Ingenieur ing = ingenieurRepo.getById((int) session.getAttribute("userId"));
			Emploi emp = repoEmploi.getById(id);
			Application application = new Application();
			application.setIngenieur(ing);
			application.setEmploi(emp);
			java.util.Date currentDate = new java.util.Date();
			Date sqlDate = new Date(currentDate.getTime());
			application.setDateApplication(sqlDate);
			try {
				if (cv != null) {
					application.setCv(cv.getBytes());
				}
				if (lettreMotivation != null) {
					application.setLettreMotivation(lettreMotivation.getBytes());
				}
				applicationRepo.save(application);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:/";
		} else {
			return "/sign-in";
		}
	}

	@GetMapping("emploiApplique")
	public String emploiAppliqué(Model model, HttpSession session) {
		if (session.getAttribute("userId") != null) {
			Ingenieur ing = ingenieurRepo.getById((int) session.getAttribute("userId"));
			model.addAttribute("ing", ing);
			List<Application> applications = applicationRepo.findAllAppByIngenieur(ing);

			model.addAttribute("applications", applications);
		}

		return "emploiAppliqué";
	}
	
	@GetMapping("/offresCategorie")
	public String getOffresByCategorie(@RequestParam("id") int categorieId, Model model) {
	    // Récupérer les offres pour la catégorie donnée depuis la base de données
	    List<Emploi> offres = repoEmploi.findByCatégorieId(categorieId);
	    
	    // Ajouter les offres à l'objet Model pour les rendre accessibles dans le modèle de vue
	    model.addAttribute("emplois", offres);
	    
	    return "searchCategorie"; // Nom de votre vue pour afficher les offres par catégorie
	}
}
