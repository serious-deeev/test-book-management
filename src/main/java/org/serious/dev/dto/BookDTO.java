package org.serious.dev.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.serious.dev.entity.Book;

@JsonPropertyOrder({"id", "title", "description", "author", "isbn", "printYear", "readAlready", "imageLink"})
@Getter
public class BookDTO {

    private final long id;
    private final String title;
    private final String description;
    private final String author;
    private final String isbn;
    private final int printYear;
    private final boolean readAlready;
    private final String imageLink;

    public BookDTO(Book book, String imageLink) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.author = book.getAuthor();
        this.isbn = book.getIsbn();
        this.printYear = book.getPrintYear();
        this.readAlready = book.isReadAlready();
        this.imageLink = imageLink;
    }
}
