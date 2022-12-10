package com.example.pamarket00.dto;

import lombok.Data;

@Data
public class commentDto {
    int commentId;
    int commentType;
    String commentUserId;
    String comment;
}
