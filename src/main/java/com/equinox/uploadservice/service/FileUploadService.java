package com.equinox.uploadservice.service;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

public interface FileUploadService {
    Mono<String> uploadSingle(FilePart filePart);
}
