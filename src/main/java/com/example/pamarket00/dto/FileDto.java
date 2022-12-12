package com.example.pamarket00.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class FileDto {
    int fileNum;
    String fileName;
    String filePath;
    int fileBoardNum;
}
