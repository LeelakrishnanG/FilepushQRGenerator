package com.file.transfer.filetransfer.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.file.transfer.filetransfer.Services.QRGenServices;
import com.file.transfer.filetransfer.DTO.QRRequest;
import com.file.transfer.filetransfer.DTO.QRResponse;

@RestController
@RequestMapping("/api/v1/qr")
public class QRGenerator {

    @Autowired
    private QRGenServices QRGenServices;

    @PostMapping("/generate")
    public ResponseEntity<QRResponse> generateQR(@RequestBody QRRequest QRID) {
        return ResponseEntity.ok(QRGenServices.generateQR(QRID));
    }
}
