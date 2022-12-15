package com.example.pamarket00.mapper;

import com.example.pamarket00.dto.BoardDto;
import com.example.pamarket00.dto.MyPageMainDto;
import com.example.pamarket00.dto.MyPageSellDto;
import com.example.pamarket00.dto.UserDto;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MyPageMapper {
    Page<MyPageMainDto> MyPageList() throws Exception;

    Page<MyPageSellDto> MyPageSellList() throws Exception;

    List<BoardDto> MyPageBuyList() throws Exception;


    public UserDto userInfo() throws Exception;


    void UpdateUserInfo(UserDto userInfo) throws Exception;

    void insertUserInfo(UserDto userDto) throws Exception;

    int idCheck(String userId) throws Exception;
}
