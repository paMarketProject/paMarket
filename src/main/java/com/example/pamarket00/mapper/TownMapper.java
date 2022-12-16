package com.example.pamarket00.mapper;

import com.example.pamarket00.dto.CommentDto;
import com.example.pamarket00.dto.TownDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface TownMapper {
    public void insertBoard(TownDto boadrDto) throws Exception;

    public List<TownDto> selectBoardList() throws Exception;

    public List<CommentDto> selectCommentList(int boardNum) throws Exception;

    public void insertComment(CommentDto comment) throws Exception;

    public TownDto selectBoardDetail(int boardNum) throws Exception;

}
