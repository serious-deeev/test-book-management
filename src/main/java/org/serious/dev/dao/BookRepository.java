package org.serious.dev.dao;

import org.serious.dev.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT book FROM Book book WHERE LOWER(book.title) LIKE LOWER(CONCAT('%', :phrase, '%')) " +
            "OR LOWER(book.description) LIKE LOWER(CONCAT('%', :phrase, '%'))")
    Page<Book> searchBooksByPhrase(String phrase, Pageable pageable);
}
