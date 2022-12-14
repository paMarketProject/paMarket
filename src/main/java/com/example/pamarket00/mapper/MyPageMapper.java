package com.example.pamarket00.mapper;

import com.example.pamarket00.dto.BoardDto;
import com.example.pamarket00.dto.MyPageMainDto;
import com.example.pamarket00.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MyPageMapper {
    List<MyPageMainDto> MyPageList() throws Exception;

    List<BoardDto> MyPageSellList() throws Exception;

    List<BoardDto> MyPageBuyList() throws Exception;


    public UserDto userInfo() throws Exception;


    void UpdateUserInfo(UserDto userInfo) throws Exception;
}
