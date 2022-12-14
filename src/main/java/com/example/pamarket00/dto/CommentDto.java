package com.example.pamarket00.dto;

import lombok.Data;

@Data
public class CommentDto {
    int commentId;
    int commentType;
    String commentUserId;
    String commentContents;
    String commentDate;
    String commentDeleted_yn;
    int commentBoardId;
}
