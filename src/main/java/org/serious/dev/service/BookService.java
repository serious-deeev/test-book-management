package org.serious.dev.service;

import org.serious.dev.dto.BookDTO;
import org.serious.dev.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> getBookById(long id);

    org.serious.dev.dto.BookDTO getBookByIdWithImageLink(long id);

    List<BookDTO> findBooksByPhrase(String phrase, int page);

    List<BookDTO> getBooks(int page);

    void saveBook(Book book);

    void markBookRead(long id);

    void updateBook(long id, Book updatedBook);
}
