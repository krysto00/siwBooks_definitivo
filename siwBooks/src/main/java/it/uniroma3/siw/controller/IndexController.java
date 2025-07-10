package it.uniroma3.siw.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class IndexController {
	
	@GetMapping(value = "/")
	public String index(Model model) {
		return "index.html";
	}

}
