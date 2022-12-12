package com.example.pamarket00.yuriDto;

import com.example.pamarket00.dto.BoardDto;
import com.example.pamarket00.dto.FileDto;
import com.example.pamarket00.dto.KategoryDto;
import com.example.pamarket00.dto.LocationDto;
import lombok.Data;

@Data
public class ProductListDto {
    private BoardDto boardDto;
    private FileDto fileDto;
    private KategoryDto kategoryDto;
    private LocationDto locationDto;
}
