package com.example.pamarket00.dto;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
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
}
