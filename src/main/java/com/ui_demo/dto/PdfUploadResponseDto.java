package com.ui_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PdfUploadResponseDto {

    private String fileName;
    private long fileSize;
    private String message;


}