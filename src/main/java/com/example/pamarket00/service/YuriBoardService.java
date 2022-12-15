package com.example.pamarket00.service;

import com.example.pamarket00.dto.BoardDto;
import com.example.pamarket00.dto.FileDto;
import com.example.pamarket00.dto.ProductBoardDto;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface YuriBoardService {
    List<ProductBoardDto> selectProductBoardList() throws Exception;
    void insertProductBoard(BoardDto board, MultipartHttpServletRequest multipart) throws Exception;
//    insertProductBoard(board,multipart)

    void updateProductBoard(BoardDto board) throws Exception;

    void deleteProductBoard(int boardNum) throws Exception;
    FileDto selectProductBoardFileInfo(int fileNum, int boardNum) throws Exception;

    ProductBoardDto selectProductBoardDetail(int boardNum) throws Exception;
}
