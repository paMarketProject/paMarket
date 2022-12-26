package com.example.pamarket00.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductBoardDto {
    private int boardNum;
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
    private String boardDate;
    private int userManner;
    private List<FileDto> fileList;
    int mapBoardNum;
    double mapX;
    double mapY;
    String boardType;
    String searchText;
}
