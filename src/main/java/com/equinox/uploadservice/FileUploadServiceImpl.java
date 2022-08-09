package com.equinox.uploadservice;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;

@Service
public class FileUploadServiceImpl implements FileUploadService {


    @Override
    public boolean uploadSingle(FilePart filePart) {
        return false;
    }
}
