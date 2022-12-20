package com.example.pamarket00.dto;
import lombok.Data;

@Data
public class CommentDto {

    private int commentNum;
    private String commentUserId;
    private String commentContents;
    private String commentDate;
    private int commentDeleted_yn;
    private int commentBoardNum;
    private int commentType;



}
