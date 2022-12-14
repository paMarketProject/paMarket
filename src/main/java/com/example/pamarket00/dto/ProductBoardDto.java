package com.example.pamarket00.dto;

import lombok.Data;

@Data
public class ProductBoardDto {
    private String kategoryName;
    private String boardTitle;
    private String boardUserId;
    private String boardContents;
    private int boardProPrice;
    private String stateName;
    private String locationName;
    private String filePath;
    private int boardHitCnt;
    private int boardLike;
}
