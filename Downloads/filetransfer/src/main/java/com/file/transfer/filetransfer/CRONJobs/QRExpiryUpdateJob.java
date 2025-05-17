package com.file.transfer.filetransfer.CRONJobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.file.transfer.filetransfer.StoredProcedures.QRStoredProcs;


@Component
@Configuration
public class QRExpiryUpdateJob {

    @Autowired
    private QRStoredProcs qrStoredProcs;

    // Runs every 5 minutes
   @Scheduled(cron = "0 */5 * * * *")
    public void runCronJob() {
        qrStoredProcs.updateQRStatus();
    }
}