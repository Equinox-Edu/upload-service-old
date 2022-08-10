package com.equinox.uploadservice;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Map;
import java.util.Optional;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.event.S3EventNotification.S3Entity;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired private AmazonS3 amazonS3;

    @Override
    public boolean uploadSingle(FilePart filePart) {
        return false;
    }


    public void upload( File inputStream) {
            PutObjectRequest customFileName = new PutObjectRequest("eqxedu", "CustomFileName", inputStream);
            amazonS3.putObject(customFileName);
    }
}
