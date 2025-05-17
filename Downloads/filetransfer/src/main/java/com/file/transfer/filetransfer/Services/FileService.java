package com.file.transfer.filetransfer.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.file.transfer.filetransfer.DTO.FileRequest;
import com.file.transfer.filetransfer.Model.FileModel;
import com.file.transfer.filetransfer.Repository.FileRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;

@Service
@RequiredArgsConstructor
public class FileService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private FileRepo fileRepo;

    public String createRecord(FileRequest fileRequest) {

        if(fileRequest.getFilePath()!=null && !fileRequest.getFilePath().isEmpty()){
            if (fileRepo.existsByFilePath(fileRequest.getFilePath())) {
                return "File already exists";
            }
            FileModel fileModel= FileModel.builder()
            .filePath(fileRequest.getFilePath())
            .fileName(fileRequest.getFileName())
            .uploadedDate(new java.util.Date())
            .DOCID(java.util.UUID.randomUUID().toString().toUpperCase())
            .build();
            fileRepo.save(fileModel);
            return "File request saved successfully";
        }
        return "File request failed";
    }

    
    public String uploadFile(MultipartFile file){
            String sql = "CALL CheckFileFormat()";

            if (fileRepo.existsByFilePath(file.getOriginalFilename())) {
                return "File already exists";
            }
          //  List<String> result = jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("fileformat"));
          //  System.out.println("Result: " + result);
            List<String> formats = jdbcTemplate.queryForList(sql, String.class);
            System.out.println("Supported formats: " + formats);
            
            if(formats.contains(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1).toUpperCase())){
            if (file != null && !file.isEmpty()) {
                try {  
                    String filePath = uploadDir + "\\"+ file.getOriginalFilename();
                    file.transferTo(new File(filePath));
                    FileModel fileModel= FileModel.builder()
                    .filePath(filePath)
                    .fileName(file.getOriginalFilename())
                    .uploadedDate(new java.util.Date())
                    .fileSize(file.getSize())
                    .DOCID(java.util.UUID.randomUUID().toString().toUpperCase())
                    .build();
                    fileRepo.save(fileModel);
                    return "File uploaded successfully";
                }
                catch (java.io.IOException e) {
                    e.printStackTrace();
                    return "File upload failed";
                }
            }
                    return "File is empty";
    }           
    return "File format not supported";
}

}
