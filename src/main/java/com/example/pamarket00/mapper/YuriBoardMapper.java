package com.example.pamarket00.mapper;

import com.example.pamarket00.dto.BoardDto;
import com.example.pamarket00.dto.FileDto;
import com.example.pamarket00.dto.ProductBoardDto;
import com.example.pamarket00.dto.UserDto;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface YuriBoardMapper {
//    List<ProductBoardDto> selectProductBoardList() throws Exception;

    Page<ProductBoardDto> selectProductBoardListPage();

    void insertProductBoard(BoardDto board) throws Exception;

    void insertMapBoard(BoardDto board) throws Exception;

    void updateProductBoard(int boardNum, String boardTitle, String boardContents, int boardProState) throws Exception;

    void deleteProductBoard(int boardNum) throws Exception;

    void insertProductBoardFileList(List<FileDto> fileList) throws Exception;

    List<FileDto> selectProductBoardFileList(int boardNum) throws Exception;

    FileDto selectProductBoardFileInfo(@Param("fileNum")int fileNum, @Param("boardNum") int boardNum) throws Exception;


//    조회수 증가
    void updateProductHitCount(int boardNum) throws Exception;
    ProductBoardDto selectProductBoardDetail(@Param("boardNum") int boardNum) throws Exception;

//      지역인증
    void locationBtnCheck(String userId, int userCheck);
}
