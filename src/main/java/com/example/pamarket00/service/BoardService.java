package com.example.pamarket00.service;

//import com.example.newboard.dto.BoardDto;

import com.example.pamarket00.dto.TownDto;

import java.util.List;



import com.example.pamarket00.dto.TownDto;
import java.util.List;

public interface BoardService {
    public List<TownDto> selectBoardList() throws Exception;

    public void insertBoard(TownDto board) throws Exception;

    public TownDto selectBoardDetail(int boardNum) throws Exception;

}
