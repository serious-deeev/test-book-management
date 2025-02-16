package org.serious.dev.service;

import org.springframework.http.ResponseEntity;

public interface FileService {

    ResponseEntity<byte[]> downloadBookImage(long id);
}
