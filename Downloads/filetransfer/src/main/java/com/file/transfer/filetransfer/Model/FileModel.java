package com.file.transfer.filetransfer.Model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import jakarta.persistence.Column;

@Data
@AllArgsConstructor
@Entity
@Table(name = "file")
@Builder
public class FileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String DOCID;
    @Column
    private String fileName;
    @Column
    private String filePath;
    @Column
    private long fileSize;
    @Column
    private String fileDescription;
    @Column
    private String queueStatus;
    @Column
    private String fileType;
    @Column
    private Date uploadedDate;
}