package com.iga.belvedere.controllers;

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class myController {

	@GetMapping("index-five.html")
	public String index5() {
		return "index-five";
	}
	@GetMapping("index-four.html")
	public String index4() {
		return "index-four";
	}
	@GetMapping("index-three.html")
	public String index3() {
		return "index-three";
	}
	@GetMapping("index-two.html")
	public String index2() {
		return "index-two";
	}
	@GetMapping("about.html")
	public String about() {
		
		return "about";
	}
	@GetMapping("account.html")
	public String account() {
		
		return "account";
	}
	@GetMapping("blog.html")
	public String blog() {
		
		return "blog";
	}
	@GetMapping("blog-details.html")
	public String blogdetails() {
		
		return "blog-details";
	}
	@GetMapping("blog-two.html")
	public String blogtwo() {
		
		return "blog-two";
	}
	@GetMapping("blog-two-2.html")
	public String blogtwos() {
		
		return "blog-two-2";
	}
	@GetMapping("candidate.html")
	public String candidate() {
		
		return "candidate";
	}
	@GetMapping("candidate-details.html")
	public String candidatedetails() {
		
		return "candidate-details";
	}
	@GetMapping("catagories.html")
	public String catagories() {
		
		return "catagories";
	}
	@GetMapping("company.html")
	public String company() {
		
		return "company";
	}
	@GetMapping("faq.html")
	public String faq() {
		
		return "faq";
	}

	@GetMapping("job-list.html")
	public String jobList() {
		
		return "job-list";
	}
	
	@GetMapping("job-details.html")
	public String jobDetail() {
		
		return "job-details";
	}
	@GetMapping("job-grid.html")
	public String jobgrid() {
		return "job-grid";
	}
	
	@GetMapping("contact.html")
	public String contact() {
		
		return "contact";
	}

	@GetMapping("pricing.html")
	public String pricing() {
		return "pricing";
	}
	@GetMapping("privacy.html")
	public String privacy() {
		return "privacy";
	}
	@GetMapping("privacy-policy.html")
	public String privacypolicy() {
		return "privacy-policy";
	}
	@GetMapping("reset-password.html")
	public String password() {
		return "reset-password";
	}
	@GetMapping("resume.html")
	public String resume() {
		return "resume";
	}
	@GetMapping("signin.html")
	public String signin() {
		return "signin";
	}
	@GetMapping("sign-in.html")
	public String signin1() {
		return "sign-in";
	}
	@GetMapping("signup.html")
	public String signup() {
		return "signup";
	}
	@GetMapping("sign-up.html")
	public String signup2() {
		return "sign-up";
	}
	@GetMapping("terms.html")
	public String terms() {
		return "terms";
	}
	@GetMapping("terms-condition.html")
	public String termscondition() {
		return "terms-condition";
	}
	@GetMapping("testimonial.html")
	public String testimonial() {
		
		return "testimonial";
	}
	
	@GetMapping("404.html")
	public String error404() {
		
		return "404";
	}
}
