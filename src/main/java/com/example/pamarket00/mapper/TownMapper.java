package com.example.pamarket00.mapper;

import com.example.pamarket00.dto.CommentDto;
import com.example.pamarket00.dto.ProductBoardDto;
import com.example.pamarket00.dto.ReviewDto;
import com.example.pamarket00.dto.TownDto;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TownMapper {
    public void insertBoard(TownDto boadrDto) throws Exception;

    void insertReviewBoard(ReviewDto reviewDto) throws Exception;


    public List<TownDto> selectBoardList() throws Exception;



    public List<CommentDto> selectCommentList(int boardNum) throws Exception;

    public void insertComment(CommentDto comment) throws Exception;

    public TownDto selectBoardDetail(int boardNum) throws Exception;

    public void commentDelete(int commentNum) throws Exception;

    Page<TownDto> selectBoardListPage();

    Page<ReviewDto> selectReviewListPage() throws Exception;



    public void boardDelete(int boardNum) throws Exception;

    public void boardUpdate(TownDto boardUpdate) throws Exception;



    void updateTownBoardHitCount(int boardNum);

}
