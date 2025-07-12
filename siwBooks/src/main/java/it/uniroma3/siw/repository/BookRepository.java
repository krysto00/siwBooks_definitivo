package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Review;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>{
	@Query("SELECT a FROM Book b JOIN b.authors a WHERE b.id = :bookId")
	Iterable<Author> findAuthorsByBookId(@Param("bookId") Long bookId);

	@Query("SELECT r FROM Book b JOIN b.reviews r WHERE b.id = :bookId")
	Iterable<Review> findReviewsByBookId(@Param("bookId") Long bookId);

	@Query("SELECT b FROM Book b JOIN b.reviews r GROUP BY b ORDER BY AVG(r.rating) DESC LIMIT 10")
	Iterable<Book> findTop10Books();

	List<Book> findByTitleContainingIgnoreCase(String title);

	@Query("SELECT b FROM Book b JOIN b.reviews r GROUP BY b HAVING ROUND(AVG(r.rating)) = :exactRating")
	List<Book> findBooksByRoundedRating(@Param("exactRating") int exactRating);
	// Ordina per titolo (ASC)
	List<Book> findAllByOrderByTitleAsc();
	
	@Query("SELECT b FROM Book b WHERE b NOT IN :books")
	Iterable<Book> findAllExcludingBooks(List<Book> books);

    
}
