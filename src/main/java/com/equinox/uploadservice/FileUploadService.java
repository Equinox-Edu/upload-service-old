package com.equinox.uploadservice;

import org.springframework.http.codec.multipart.FilePart;

public interface FileUploadService {
    boolean uploadSingle(FilePart filePart);
}
