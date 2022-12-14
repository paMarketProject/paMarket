package com.example.pamarket00.mapper;

import com.example.pamarket00.dto.BoardDto;
import com.example.pamarket00.dto.FileDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.File;
import java.util.List;
@Mapper
public interface YuriBoardMapper {
    List<BoardDto> selectProductBoardList() throws Exception;

    void insertProductBoard(BoardDto board) throws Exception;

    void updateProductBoard(BoardDto board) throws Exception;

    void deleteProductBoard(int boardNum) throws Exception;

    void insertProductBoardFileList(List<FileDto> fileList) throws Exception;

    List<FileDto> selectProductBoardFileList(int boardNum) throws Exception;

    FileDto selectProductBoardFileInfo(@Param("fileNum")int fileNum, @Param("boardNum") int boardNum) throws Exception;



}
