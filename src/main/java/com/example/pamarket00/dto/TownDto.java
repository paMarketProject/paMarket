package com.example.pamarket00.dto;
import lombok.Data;

@Data
public class TownDto {
    private int boardNum;
    private String boardTitle;
    private String boardUserId;
    private int boardKategory;
    private String boardContents;
    private String boardDate;
    private int boardHitCnt;


}
