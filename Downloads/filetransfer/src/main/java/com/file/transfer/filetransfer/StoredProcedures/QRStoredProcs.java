package com.file.transfer.filetransfer.StoredProcedures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class QRStoredProcs {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getQRDocid(Long id) {
        try {
            // Use parameterized query to prevent SQL injection
            String sql = "CALL getQRDocid(?)";
            String docID = jdbcTemplate.queryForObject(sql, String.class, id);
            System.out.println("DocID:------> " + docID);
            return docID;
        } catch (Exception e) {
            System.err.println("Error while fetching QR DocID: " + e.getMessage());
            return null; // Or throw a custom exception if needed
        }
    }

    /**
     * Calls the stored procedure 'setQRStatus' to update the QR status.
     */
    public void updateQRStatus() {
        try {
            int rowsAffected = jdbcTemplate.update("CALL setQRStatus()");
            System.out.println("QR status updated successfully. Rows affected: " + rowsAffected);
        } catch (Exception e) {
            System.err.println("Error while updating QR status: " + e.getMessage());
        }
    }

    public String getDocUrl(Long id) {
        // TODO Auto-generated method stub
        
        try {
            // Use parameterized query to prevent SQL injection
            String sql = "CALL getDocURL(?)";
            String DocURL = jdbcTemplate.queryForObject(sql, String.class, id);
            System.out.println("DocURL:------> " + DocURL);
            return DocURL;
        } catch (Exception e) {
            System.err.println("Error while fetching Doc URL for QR: " + e.getMessage());
            return null; // Or throw a custom exception if needed
        }
    }
}