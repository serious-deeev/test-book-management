package org.serious.dev.service.impl;

import org.serious.dev.config.PaginationProperties;
import org.serious.dev.repository.BookRepository;
import org.serious.dev.dto.BookDTO;
import org.serious.dev.entity.Book;
import org.serious.dev.exception.BookIsAlreadyExistsException;
import org.serious.dev.exception.BookIsAlreadyReadException;
import org.serious.dev.exception.NoSuchBookException;
import org.serious.dev.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BookServiceImpl implements BookService {

    private final String baseUrl;
    private final String baseFileApi;
    private final int serverPort;
    private final BookRepository bookRepository;
    private final PaginationProperties paginationProperties;

    @Autowired
    public BookServiceImpl(
            @Value("${app.base-url}") String baseUrl,
            @Value("${app.base-file-api}") String baseFileApi,
            @Value("${server.port}")
            int serverPort,
            BookRepository bookRepository,
            PaginationProperties paginationProperties) {
        this.baseUrl = baseUrl;
        this.baseFileApi = baseFileApi;
        this.serverPort = serverPort;
        this.bookRepository = bookRepository;
        this.paginationProperties = paginationProperties;
    }

    @Override
    public Optional<Book> getBookById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public BookDTO getBookByIdWithImageLink(long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchBookException(id));

        String imageLink = generateImageLink(book.getId());
        return new org.serious.dev.dto.BookDTO(book, imageLink);
    }

    @Override
    public List<BookDTO> findBooksByPhrase(String phrase, int page) {
        Pageable pageable = PageRequest.of(page, paginationProperties.getPageSize());

        return bookRepository
                .searchBooksByPhrase(phrase, pageable)
                .stream()
                .map(book -> new BookDTO(book, generateImageLink(book.getId())))
                .toList();
    }

    @Override
    public List<BookDTO> getBooks(int page) {
        Pageable pageable = PageRequest.of(page, paginationProperties.getPageSize());
        return bookRepository
                .findAll(pageable)
                .getContent()
                .stream()
                .map(book -> new BookDTO(book, generateImageLink(book.getId())))
                .toList();
    }

    private String generateImageLink(long id) {
        return baseUrl + ":" + serverPort + baseFileApi + id + "/download";
    }

    @Override
    public void saveBook(Book book) {
        Book newBook = bookRepository.findById(book.getId())
                .orElseThrow(() -> new BookIsAlreadyExistsException(book.getIsbn()));

        bookRepository.save(newBook);
    }

    @Override
    public void markBookRead(long id) {
        Optional<Book> optionalBook = getBookById(id);
        Book book = optionalBook
                .orElseThrow(() -> new NoSuchBookException(id));

        if (book.isReadAlready()) {
            throw new BookIsAlreadyReadException(id);
        }

        book.setReadAlready(true);
        saveBook(book);
    }

    @Override
    public void updateBook(long id, Book updatedBook) {
        Optional<Book> optionalBook = getBookById(id);
        Book book = optionalBook
                .orElseThrow(() -> new NoSuchBookException(id));

        book.setTitle(updatedBook.getTitle());
        book.setDescription(updatedBook.getDescription());
        book.setIsbn(updatedBook.getIsbn());
        book.setPrintYear(updatedBook.getPrintYear());
        book.setReadAlready(false);
        book.setImage(updatedBook.getImage());
        saveBook(book);
    }
}
