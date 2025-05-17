package com.file.transfer.filetransfer.ProcessorandActions;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

@Component
public class QRWriter {

    @Value("${QR.generate-dir}")
    private String QRDir;

    public String QRImagewriter(String contents){
                Path filePath= Paths.get(QRDir+"\\"+new Date().getTime()+".png");
                        try {
                            int width = 400;
                            int height = 400;
 
                            QRCodeWriter qrCodeWriter = new QRCodeWriter();

                            Map<EncodeHintType, Object> hints = new HashMap<>();
                            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

                            BitMatrix bitMatrix = qrCodeWriter.encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
                            
                            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", filePath);

                            System.out.println("QR Code generated successfully!");
                        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
                                return filePath.toString();
    }
}
