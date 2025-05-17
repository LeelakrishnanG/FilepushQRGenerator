package com.file.transfer.filetransfer.Repository;

import com.file.transfer.filetransfer.Model.FileModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepo extends JpaRepository<FileModel, Long> {
    public boolean existsByFilePath(String filePath);
}
