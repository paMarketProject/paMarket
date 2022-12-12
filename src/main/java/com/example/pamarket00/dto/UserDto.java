package com.example.pamarket00.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserDto {
    int userNum;
    String userName;
    String userId;
    String userPw;
    String userAdd1;
    String userAdd2;
    String userManner;
    String userTel;
    String userEmail;
    int userGender;
    int userCheck;
    int userLike;
}
