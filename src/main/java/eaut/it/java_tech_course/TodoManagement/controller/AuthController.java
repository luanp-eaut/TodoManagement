package eaut.it.java_tech_course.TodoManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
	@GetMapping("/login")
	public String login() {
		return "login";
	}	@GetMapping("/logout")
	public String logout() {
		return "redirect:/login?logout";
	}
	
}
