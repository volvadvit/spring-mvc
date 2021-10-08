package com.volvadvit.talkie.service;

import com.volvadvit.talkie.domain.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileUploadService {

    @Value("${files.upload.path}") private String uploadFilesPath;

    public void uploadFile(Message message, MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            // make file dir
            File uploadFileDir = new File(uploadFilesPath);
            if (!uploadFileDir.exists()) {
                uploadFileDir.mkdir();
            }

            String fileUUID = UUID.randomUUID().toString();
            String filename = fileUUID + "." + file.getOriginalFilename();
            message.setFilename(filename);

            //download file
            file.transferTo(new File(uploadFileDir + "/" + filename));
        }
    }
}
