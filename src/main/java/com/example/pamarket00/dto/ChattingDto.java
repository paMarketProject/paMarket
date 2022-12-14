package com.example.pamarket00.dto;

import lombok.Data;

@Data
public class ChattingDto {
    int chatNum;
    int chatRoomNum;
    String chatFromUserId;
    String chatToUserId;
    String chatMessage;
    String chatDate;
    String chatImageFilePath;
}
