package com.example.pamarket00.dto;

import lombok.Data;

@Data
public class ReviewDto {
    int reviewNum;
    String reviewToUserId;
    String reviewFromUserId;
    int reviewScore;
    String reviewDate;
    String reviewContents;
}
