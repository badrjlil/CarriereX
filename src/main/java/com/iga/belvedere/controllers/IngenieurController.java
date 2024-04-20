package com.iga.belvedere.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iga.belvedere.entities.Emploi;
import com.iga.belvedere.repositories.emploiRepository;

@Controller
public class IngenieurController {

	@Autowired
	private emploiRepository repoEmploi;
	
	@GetMapping("/findJob")
	public String findJob(@RequestParam String keyword, @RequestParam String location, @RequestParam String category, Model model) {
		List<Emploi> emplois = repoEmploi.findAllByKeyword(keyword);
		model.addAttribute("emplois", emplois);
		return "job-list";
	}
	
}
