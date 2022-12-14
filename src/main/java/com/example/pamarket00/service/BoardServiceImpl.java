package com.example.pamarket00.service;
import com.example.pamarket00.dto.TownDto;
import com.example.pamarket00.mapper.TownMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private TownMapper TownMapper;

    @Override
    public List<TownDto> selectBoardList() throws Exception {
        return TownMapper.selectBoardList();
    }

    @Override
    public void insertBoard(TownDto board) throws Exception {
        TownMapper.insertBoard(board);
    }

    @Override
    public TownDto selectBoardDetail(int boardNum) throws Exception {
        TownDto townBoard = TownMapper.selectBoardDetail(boardNum);
        return townBoard;
    }

}

