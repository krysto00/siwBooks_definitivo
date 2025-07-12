package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.service.AuthorService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AuthorController {

	@Autowired private AuthorService authorService;
	//@Autowired private BookService bookService;
	//@Autowired private ImageService imageService;

	@GetMapping("/author")
	public String showAuthors(Model model) {
		model.addAttribute("authors", this.authorService.getAllAuthors());
		return "authors.html";
	}
	
	@GetMapping("/author/{id}")
	public String showAuthors(@PathVariable("id") Long id, @RequestParam(name = "from", defaultValue = "/author") String from, Model model, HttpServletRequest request) {
		Author author = this.authorService.getAuthorById(id);
		model.addAttribute("author", author);
		model.addAttribute("books", this.authorService.findBooksByAuthorId(id));
		model.addAttribute("photo", author.getPhoto());
		model.addAttribute("backUrl", from);

		return "author.html";
	}


}
