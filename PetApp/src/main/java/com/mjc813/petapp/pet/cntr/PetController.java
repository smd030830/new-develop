package com.mjc813.petapp.pet.cntr;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
public class PetController {

	@RequestMapping("/")
	public String homePet(Model model) {
		model.addAttribute("curTime", LocalDateTime.now().toString());
		return "home";
	}

	@RequestMapping("/pet")
	public String pet(Model model) {
		return "/pet/index";
	}
}
