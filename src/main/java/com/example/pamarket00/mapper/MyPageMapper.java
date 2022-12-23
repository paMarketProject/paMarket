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
    Page<MyPageMainDto> MyPageList(String userId) throws Exception;

    Page<MyPageSellDto> MyPageSellList(String userId) throws Exception;

    List<BoardDto> MyPageBuyList() throws Exception;

    void UpdateUserInfo(UserDto userInfo) throws Exception;

    void insertUserInfo(UserDto userDto) throws Exception;

    int idCheck(String userId) throws Exception;

    UserDto loginCheck(String userId, String userPw) throws Exception;

    UserDto newSession(UserDto userDto) throws Exception;
}