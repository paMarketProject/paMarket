package com.example.pamarket00.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardDto {
    int boardNum;
    String boardTitle;
    String boardType;
    int boardKategory;
    int boardProState;
    String boardUserId;
    String boardContents;
    String boardDate;
    int boardUserCheck;
    String boardImagePath;
    int boardProPrice;
    String deleted_yn;
    int boardLike;
    int boardHitCnt;
    int mapBoardNum;
    double mapX;
    double mapY;
}
