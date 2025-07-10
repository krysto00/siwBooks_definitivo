package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

	Optional<Author> findByName(String authorName);

}
