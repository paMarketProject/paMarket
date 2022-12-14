package com.example.pamarket00.service;

import com.example.pamarket00.common.FileUtils;
import com.example.pamarket00.common.XyUtils;
import com.example.pamarket00.dto.BoardDto;
import com.example.pamarket00.dto.FileDto;
import com.example.pamarket00.dto.ProductBoardDto;
import com.example.pamarket00.dto.UserDto;
import com.example.pamarket00.mapper.YuriBoardMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@Service
public class YuriBoardServiceImpl implements YuriBoardService {
    @Autowired
    private YuriBoardMapper yuriBoardMapper;
    @Autowired
    private FileUtils fileUtils;

    //    @Autowired
//    private XyUtils xyUtils;
    @Override
    public Page<ProductBoardDto> selectProductBoardList(int pageNum) throws Exception {
        PageHelper.startPage(pageNum, 8);
        return yuriBoardMapper.selectProductBoardListPage();
    }

    //    검색한 값 조회
    @Override
    public Page<ProductBoardDto> search(int pageNum, String searchText) throws Exception {
        PageHelper.startPage(pageNum, 8);
        return yuriBoardMapper.search(searchText);
    }



//    게시물저장
    @Override
    public void insertProductBoard (BoardDto board, MultipartHttpServletRequest uploadFiles) throws Exception{
        yuriBoardMapper.insertProductBoard(board);
//        board = xyUtils.parseXyInfo(board.getBoardNum(), board.getMapX(), board.getMapY());
        yuriBoardMapper.insertMapBoard(board);
//        yuriBoardMapper.insert
        List<FileDto> fileList = fileUtils.parseFileInfo(board.getBoardNum(),uploadFiles);

        if(CollectionUtils.isEmpty(fileList) == false){
            yuriBoardMapper.insertProductBoardFileList(fileList);
        }
    }

//    게시글 수정
    @Override
    public void updateProductBoard(int boardNum, String boardTitle, String boardContents, int boardProState) throws Exception {
        yuriBoardMapper.updateProductBoard(boardNum,boardTitle,boardContents,boardProState);
    }

//    게시글 삭제
    @Override
    public void deleteProductBoard(int boardNum) throws Exception {
        yuriBoardMapper.deleteProductBoard(boardNum);
    }

    @Override
    public FileDto selectProductBoardFileInfo(int fileNum, int boardNum) throws Exception {
        return yuriBoardMapper.selectProductBoardFileInfo(fileNum,boardNum);
    }

    @Override
    public ProductBoardDto selectProductBoardDetail(int boardNum) throws Exception{
        yuriBoardMapper.updateProductHitCount(boardNum);
        ProductBoardDto board = yuriBoardMapper.selectProductBoardDetail(boardNum);
        List<FileDto> fileList = yuriBoardMapper.selectProductBoardFileList(boardNum);
        board.setFileList(fileList);

        return board;
    }

//    @Override
//    public UserDto selectLocationInfo () throws Exception{
//        return yuriBoardMapper.selectLocationInfo();
//    }

//    지역인증
    @Override
    public void locationBtnCheck(String userId, int userCheck){
        yuriBoardMapper.locationBtnCheck(userId, userCheck);
    }
}
