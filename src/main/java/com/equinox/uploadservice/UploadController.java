package com.equinox.uploadservice;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Controller
public class UploadController {

    @Autowired
    FileUploadServiceImpl fileUploadService;

    @GetMapping(value = "/upload")
    public Mono<ServerResponse> upload(@RequestPart("file") Mono<FilePart> filePartMono) {
        return Mono.create(monoSink -> {
                var path = "/Users/sahak1an/IdeaProjects/upload-service/src/main/resources/application.properties";

                fileUploadService.upload(new File(path));
            })
            .flatMap(aBoolean -> ServerResponse.ok().build());
    }
}
