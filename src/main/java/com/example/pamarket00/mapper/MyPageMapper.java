package com.example.pamarket00.mapper;

import com.example.pamarket00.dto.BoardDto;

import java.util.List;
@Mapper
public interface MyPageMapper {
    List<BoardDto> boardList() throws Exception;
}
