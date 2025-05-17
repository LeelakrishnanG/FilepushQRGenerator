package com.file.transfer.filetransfer.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.file.transfer.filetransfer.DTO.FileRequest;
import com.file.transfer.filetransfer.Services.FileService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/request")
    public ResponseEntity<String> requestFile(@RequestBody FileRequest fileRequest) {
        // Logic to handle file request
        return ResponseEntity.ok(fileService.createRecord(fileRequest));
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if(file.isEmpty()){
            return ResponseEntity.badRequest().body("File is empty");
        }
        return ResponseEntity.ok(fileService.uploadFile(file));
    }
}
