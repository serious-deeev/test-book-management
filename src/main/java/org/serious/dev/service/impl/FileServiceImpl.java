package org.serious.dev.service.impl;

import org.serious.dev.enums.ImageExtension;
import org.serious.dev.repository.BookRepository;
import org.serious.dev.entity.Book;
import org.serious.dev.exception.NoSuchBookException;
import org.serious.dev.service.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Transactional
@Service
public class FileServiceImpl implements FileService {

    private final BookRepository bookRepository;

    public FileServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public ResponseEntity<byte[]> downloadBookImage(long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchBookException(id));

        byte[] imageData = book.getImage();
        String encodedFileName = encodedFileName(book.getTitle());
        String contentType = checkContentType(imageData);
        String extension = getExtensionFromContentType(contentType);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encodedFileName + extension);
        headers.set(HttpHeaders.CONTENT_TYPE, contentType);

        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }

    /**
     * Кодируем кириллицу в названии файла в application/x-www-form-urlencoded.
     * Кодирование через URLEncoder заменит пробелы на символ +.
     * Но согласно URL-encoding пробел – %20.
     * Поэтому с помощью replaceAll заменяем символы.
     * Иначе при скачивании картинки имя будет: "Психология+и+здравый+смысл.jpg".
     */
    private String encodedFileName(String title) {
        return URLEncoder
                .encode(title, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");
    }

    // определяем content-type файла по первым байтам потока
    private String checkContentType(byte[] imageData) {
        String contentType;
        try (ByteArrayInputStream bais = new ByteArrayInputStream(imageData)) {
            contentType = URLConnection.guessContentTypeFromStream(bais);
        } catch (Exception e) {
            contentType = "application/octet-stream";
        }

        return contentType;
    }

    // сформировать расширение для скачиваемого файла, исходя из его content-type
    private String getExtensionFromContentType(String contentType) {
        return ImageExtension.getImageExtension(contentType);
    }
}
