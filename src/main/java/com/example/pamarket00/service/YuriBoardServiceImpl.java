package com.example.pamarket00.service;

import com.example.pamarket00.dto.BoardDto;
import com.example.pamarket00.mapper.YuriBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class YuriBoardServiceImpl implements YuriBoardService{
    @Autowired
    private YuriBoardMapper yuriBoardMapper;

    @Override
    public List<BoardDto> selectBoardList() throws Exception{
        return yuriBoardMapper.selectBoardList();
    }

}
