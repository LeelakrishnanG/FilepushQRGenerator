package com.file.transfer.filetransfer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.file.transfer.filetransfer.Model.QRGenModel;

@Repository
public interface QRGenRepo  extends JpaRepository<QRGenModel, Long> {

}
