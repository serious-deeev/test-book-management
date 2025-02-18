package org.serious.dev.controller;

import org.serious.dev.dto.BookDTO;
import org.serious.dev.entity.Book;
import org.serious.dev.service.BookService;
import org.serious.dev.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookController {

    private final BookService bookService;
    private final FileService fileService;

    @Autowired
    public BookController(BookService bookService, FileService fileService) {
        this.bookService = bookService;
        this.fileService = fileService;
    }

    @GetMapping("/books/{id}")
    public org.serious.dev.dto.BookDTO getBookInfo(@PathVariable Long id) {
        return bookService.getBookByIdWithImageLink(id);
    }

    @GetMapping("/files/{id}/download")
    public ResponseEntity<byte[]> downloadBookImage(@PathVariable Long id) {
        return fileService.downloadBookImage(id);
    }

    @GetMapping("/books/search")
    public List<BookDTO> findBooksByPhrase(@RequestParam(name = "phrase") String phrase, @RequestParam(name = "page") Integer page) {
        return bookService.findBooksByPhrase(phrase, page);
    }

    @GetMapping("/books")
    public List<BookDTO> getBooks(@RequestParam(name = "page") Integer page) {
        return bookService.getBooks(page);
    }

    @PostMapping("/books")
    public void createNewBook(@RequestBody Book book) {
        bookService.saveBook(book);
    }

    @PatchMapping("/books/{id}")
    public void markBookRead(@PathVariable Long id) {
        bookService.markBookRead(id);
    }

    @PutMapping("/books/{id}")
    public void updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        bookService.updateBook(id, updatedBook);
    }
}
