package com.example.pamarket00.service;
import com.example.pamarket00.dto.BoardDto;
import com.example.pamarket00.dto.MyPageMainDto;
import com.example.pamarket00.dto.MyPageSellDto;
import com.example.pamarket00.dto.UserDto;

import java.util.List;
public interface MyPageService {

    List<MyPageMainDto> MyPageList(int pageNum) throws Exception;

    List<MyPageSellDto> MyPageSellList(int pageNum) throws Exception;

    List<BoardDto> MyPageBuyList() throws Exception;

    public UserDto userInfo() throws Exception;

    void UpdateUserInfo(UserDto userInfo) throws Exception;

    void insertUserInfo(UserDto userDto) throws Exception;

    int IdCheck(String userId) throws Exception;
}
