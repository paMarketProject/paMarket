package com.example.pamarket00.service;

//import com.example.newboard.dto.BoardDto;

import com.example.pamarket00.dto.CommentDto;
import com.example.pamarket00.dto.TownDto;
import com.github.pagehelper.Page;

import java.util.List;

public interface BoardService {
    public List<TownDto> selectBoardList() throws Exception;

    public void insertBoard(TownDto board) throws Exception;

    public TownDto selectBoardDetail(int boardNum) throws Exception;

    List<CommentDto> selectCommentList(int boardNum) throws Exception;

    public void insertComment( String commentUserId, String commentContents, int commentBoardNum) throws Exception;

    public void commentDelete(int commentBoardNum, int commentNum) throws Exception;

    Page<TownDto> selectBoardList(int pageNo);

    public void boardDelete(int boardNum) throws Exception;


    void boardUpdate(String boardTitle, String boardContents, int boardNum) throws Exception;
}
