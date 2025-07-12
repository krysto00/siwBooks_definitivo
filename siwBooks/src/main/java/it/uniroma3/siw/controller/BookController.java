package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.service.BookService;

@Controller
public class BookController {
	
	@Autowired private BookService bookService;
	
	@GetMapping("/book")
	public String showBooks(Model model) {
		model.addAttribute("books", this.bookService.getAllBooks());
		return "books.html";
	}
	
	@GetMapping("/book/{bookId}")
	public String getBook(@PathVariable("bookId") Long bookId,
			@RequestParam(name = "from", defaultValue = "/books") String from,
			Model model) {
		Book book = this.bookService.getBookbyId(bookId);
		model.addAttribute("book", book);
		model.addAttribute("authors", this.bookService.findAuthorsByBookId(bookId));
		model.addAttribute("cover", book.getCover());
		model.addAttribute("newReview", new Review());
		//model.addAttribute("lastReview", this.reviewService.getLast5Review(book));
		model.addAttribute("backUrl", from);

		return "book.html";
	}

}
