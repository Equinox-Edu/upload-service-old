package com.equinox.uploadservice.service;

import static java.util.UUID.randomUUID;

import java.io.InputStream;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    private final AmazonS3 amazonS3;

    @Value("${aws.bucket-name}")
    private String bucketName;

    public FileUploadServiceImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public Mono<String> uploadSingle(FilePart filePart) {
        return DataBufferUtils
                .join(filePart.content())
                .metrics()
                .tag("upload-count","count")
                .map(this::uploadDataBuffer);
    }

    private String uploadDataBuffer(DataBuffer dataBuffer) {
        var fileInputStream = dataBuffer.asInputStream();

        return uploadInputStream(fileInputStream);
    }

    public String uploadInputStream(InputStream inputStream) {
        var fileName = randomUUID().toString();
        var metadata = new ObjectMetadata();

        var customFileName = new PutObjectRequest(bucketName, fileName, inputStream, metadata);
        var putObjectResult = amazonS3.putObject(customFileName);

        return putObjectResult.getContentMd5();
    }
}
