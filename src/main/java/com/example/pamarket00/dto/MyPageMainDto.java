package com.example.pamarket00.dto;

import lombok.Data;

@Data
public class MyPageMainDto {
    private String boardUserId;
    private String boardTitle;
    private String kategory;
    private String boardDate;
    private int boardHitCnt;
    private int boardNum;
}
