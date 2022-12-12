package com.example.pamarket00.service;

import com.example.pamarket00.dto.BoardDto;
import com.example.pamarket00.mapper.MyPageMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyPageServiceImpl implements MyPageService{
    @Autowired
    private MyPageMapper myPageMapper;

    @Override
    public List<BoardDto> boardList() throws Exception{
        return MyPageMapper.boardList();
    }

}
