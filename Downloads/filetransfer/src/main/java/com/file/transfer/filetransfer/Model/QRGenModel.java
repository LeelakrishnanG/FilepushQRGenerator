package com.file.transfer.filetransfer.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@Table(name = "qrgen")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QRGenModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String docid;
    @Column
    private Date creationDate;
    @Column
    private Date expirationDate;
    @Column
    private String status;
}
