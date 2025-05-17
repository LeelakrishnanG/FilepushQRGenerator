package com.file.transfer.filetransfer.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileRequest {

    @NotBlank(message = "File path cannot be blank")
    private String filePath;
    @NotBlank(message = "File name cannot be blank")
    private String fileName;
}