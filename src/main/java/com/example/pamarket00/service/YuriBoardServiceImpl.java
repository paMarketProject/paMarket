package com.example.pamarket00.service;

import com.example.pamarket00.common.FileUtils;
import com.example.pamarket00.dto.BoardDto;
import com.example.pamarket00.dto.FileDto;
import com.example.pamarket00.mapper.YuriBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@Service
public class YuriBoardServiceImpl implements YuriBoardService{
    @Autowired
    private YuriBoardMapper yuriBoardMapper;

    private FileUtils productFileUtils;

    @Override
    public List<BoardDto> selectProductBoardList() throws Exception{
        List<BoardDto> list = null;
        list = yuriBoardMapper.selectProductBoardList();
        return list;
    }

//    게시물저장
    @Override
    public void insertProductBoard (BoardDto board, MultipartHttpServletRequest uploadFiles) throws Exception{
        yuriBoardMapper.insertProductBoard(board);

        List<FileDto> productFileList = productFileUtils.parseFileInfo(board.getBoardNum(),uploadFiles);

        if(CollectionUtils.isEmpty(productFileList) == false){
            yuriBoardMapper.insertProductBoardFileList(productFileList);
        }
    }

    @Override
    public void updateProductBoard(BoardDto board) throws Exception {
        yuriBoardMapper.updateProductBoard(board);
    }

    @Override
    public void deleteProductBoard(int boardNum) throws Exception {
        yuriBoardMapper.deleteProductBoard(boardNum);
    }

    @Override
    public FileDto selectProductBoardFileInfo(int fileNum, int boardNum) throws Exception {
        return yuriBoardMapper.selectProductBoardFileInfo(fileNum,boardNum);
    }

}
