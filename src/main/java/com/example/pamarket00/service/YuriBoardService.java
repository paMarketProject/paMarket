package com.example.pamarket00.service;

import com.example.pamarket00.dto.BoardDto;
import org.springframework.stereotype.Component;

import java.util.List;

public interface YuriBoardService {
    List<BoardDto> selectBoardList() throws Exception;
}
