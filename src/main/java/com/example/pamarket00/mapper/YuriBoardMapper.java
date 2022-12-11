package com.example.pamarket00.mapper;

import com.example.pamarket00.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface YuriBoardMapper {
    List<BoardDto> selectBoardList() throws Exception;

}
