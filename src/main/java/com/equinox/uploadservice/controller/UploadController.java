package com.equinox.uploadservice.controller;

import com.equinox.uploadservice.service.FileUploadServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UploadController {

    private final FileUploadServiceImpl fileUploadService;

    public UploadController(FileUploadServiceImpl fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<?>> upload(@RequestPart("file") Mono<FilePart> filePartMono) {
        return filePartMono
                .flatMap(fileUploadService::uploadSingle)
                .map(ResponseEntity::ok);
    }
}
