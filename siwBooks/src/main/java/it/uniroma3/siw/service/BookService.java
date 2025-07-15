package it.uniroma3.siw.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.repository.BookRepository;
import jakarta.validation.Valid;

@Service
@Transactional
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
	
	public List<Book> findBooksByTitle(String query) {
	    return bookRepository.findByTitleContainingIgnoreCase(query);
	}
	
	public List<Book> findBooksByRating(int exactRating) {
	    return this.bookRepository.findBooksByRoundedRating(exactRating);
	}
	
	public Iterable<Book> findTop10Books() {
		return this.bookRepository.findTop10Books();
	}
	
	public void save(@Valid Book book) {
		this.bookRepository.save(book);
	}
	
	public void delete(Book book) {
		this.bookRepository.delete(book);
	}
	
	public Iterable<Review> findReviewsByBookId(Long id) {
	       return this.bookRepository.findReviewsByBookId(id);
	    }
	
	public  Iterable<Book> findAllExcludingBooks(List<Book> books) {
		return this.bookRepository.findAllExcludingBooks(books);
	}
	
	public Set<Book> getBooksByIds(Set<Long> selectedBookIds) {
		Iterable<Book> booksIterable = bookRepository.findAllById(selectedBookIds);
		Set<Book> booksSet = new HashSet<>();
		booksIterable.forEach(booksSet::add);
		return booksSet;
	}

}
