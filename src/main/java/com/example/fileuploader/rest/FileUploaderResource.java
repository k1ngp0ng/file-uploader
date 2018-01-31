package com.example.fileuploader.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestController
public class FileUploaderResource {

    private static final Logger logger = LoggerFactory.getLogger(FileUploaderResource.class);

    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    @Value("${root.path}")
    private String rootPath;

    @PostMapping(path = "/api/clients/{id}/upload-file/{fileName:.+}")
    public ResponseEntity<?> uploadFile(@PathVariable("id") String clientId,
                                        @PathVariable("fileName") String fileName,
                                        @RequestPart("file") MultipartFile uploadedFile) throws Exception {
        LocalDateTime now = LocalDateTime.now();
        String directoryPath = String.format("%s/%s/%s", rootPath, clientId, now.format(timeFormatter));
        File directory = new File(directoryPath);
        directory.mkdirs();
        uploadedFile.transferTo(new File(directory + "/" + fileName));
        logger.info("File '{}' written with success on disk, in directory {}", fileName, directoryPath);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
