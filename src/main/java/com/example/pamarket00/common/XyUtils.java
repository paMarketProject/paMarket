package com.example.pamarket00.common;

import com.example.pamarket00.dto.BoardDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class XyUtils {
    public BoardDto parseXyInfo(int mapBoardNum, double beforeComma, double afterComma) throws Exception{
        BoardDto board = new BoardDto();
        board.setMapBoardNum(mapBoardNum);
        board.setMapX(beforeComma);
        board.setMapY(afterComma);


        return board;
    }

    }

