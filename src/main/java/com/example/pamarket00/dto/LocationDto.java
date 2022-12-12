package com.example.pamarket00.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LocationDto {
    int locationNum;
    String locationName;
}
