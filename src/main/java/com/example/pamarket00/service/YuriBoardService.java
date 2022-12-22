package com.example.pamarket00.service;

import com.example.pamarket00.dto.BoardDto;
import com.example.pamarket00.dto.FileDto;
import com.example.pamarket00.dto.ProductBoardDto;
import com.example.pamarket00.dto.UserDto;
import com.github.pagehelper.Page;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface YuriBoardService {
//    List<ProductBoardDto> selectProductBoardList() throws Exception;
//    Page<ProductBoardDto> selectProductBoardList(int pageNo) throws Exception;
    
    
    Page<ProductBoardDto> selectProductBoardList(int pageNum) throws Exception;
    
//    글, 사진, 좌표 삽입
    void insertProductBoard(BoardDto board, MultipartHttpServletRequest multipart) throws Exception;
//    insertProductBoard(board,multipart)

//    게시글 수정
    void updateProductBoard(int boardNum, String boardTitle, String boardContents, int boardProState) throws Exception;

//    게시글 삭제
    void deleteProductBoard(int boardNum) throws Exception;
    FileDto selectProductBoardFileInfo(int fileNum, int boardNum) throws Exception;

    ProductBoardDto selectProductBoardDetail(int boardNum) throws Exception;
    
//    동네인증 페이지 값 가져오기
    UserDto selectLocationInfo (String userId) throws Exception;
    
}
