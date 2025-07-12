package it.uniroma3.siw.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired private BookRepository bookRepository;

	public Iterable<Book> getAllBooks() {
		return this.bookRepository.findAll();
	}
	
	public Book getBookbyId(Long id) {
		return this.bookRepository.findById(id).orElse(null);
	}

	public Iterable<Author> findAuthorsByBookId(Long bookId) {
		return this.bookRepository.findAuthorsByBookId(bookId);
	}
	
	public Set<Author> getAuthorsByBookId(Long bookId) {
		Iterable<Author> authorsIterable = this.bookRepository.findAuthorsByBookId(bookId);
		Set<Author> authorsSet = new HashSet<>();
		authorsIterable.forEach(authorsSet::add);
		return authorsSet;
	}

}
