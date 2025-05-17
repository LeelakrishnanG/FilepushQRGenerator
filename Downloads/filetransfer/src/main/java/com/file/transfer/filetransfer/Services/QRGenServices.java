package com.file.transfer.filetransfer.Services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.file.transfer.filetransfer.DTO.QRRequest;
import com.file.transfer.filetransfer.DTO.QRResponse;
import com.file.transfer.filetransfer.Model.QRGenModel;
import com.file.transfer.filetransfer.ProcessorandActions.QRWriter;
import com.file.transfer.filetransfer.Repository.QRGenRepo;
import com.file.transfer.filetransfer.StoredProcedures.QRStoredProcs;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class QRGenServices {

    @Autowired
    private QRGenRepo qrGenRepository;

    @Autowired
    private QRWriter qrWriter;

    private QRGenModel qrGenModel;

    @Autowired
    private QRStoredProcs qrStoredProcs;

    public QRResponse generateQR(QRRequest qrRequest) {
        
        try {
            System.out.println(qrRequest.getId());
            qrGenModel = QRGenModel.builder()
                    .docid(qrStoredProcs.getQRDocid(qrRequest.getId()))
                    .creationDate(new Date())
                    .expirationDate(new Date(System.currentTimeMillis() + 60000 * 5))
                    .status("Active")
                    .build();
            
            String docURL = qrStoredProcs.getDocUrl(qrRequest.getId());
            qrWriter.QRImagewriter(docURL);

            qrGenRepository.save(qrGenModel);
            return QRResponse.builder()
                    .message("Success")
                    .build();
        
        } catch (Exception e) {
            e.printStackTrace();
        }

        

        return QRResponse.builder()
                .message("Failed")
                .build();
    }

}