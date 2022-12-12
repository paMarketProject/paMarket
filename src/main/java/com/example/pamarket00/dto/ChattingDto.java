package com.example.pamarket00.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ChattingDto {
    int chatNum;
    int chatRoomNum;
    String chatFromUserId;
    String chatToUserId;
    String chatMessage;
    String chatDate;
    String chatImageFilePath;
}
