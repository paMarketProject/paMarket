package com.example.pamarket00.service;
import com.example.pamarket00.dto.BoardDto;
import com.example.pamarket00.dto.MyPageMainDto;
import com.example.pamarket00.dto.UserDto;

import java.util.List;
public interface MyPageService {

    List<MyPageMainDto> MyPageList() throws Exception;

    List<BoardDto> MyPageSellList() throws Exception;

    List<BoardDto> MyPageBuyList() throws Exception;

    public UserDto userInfo() throws Exception;

    void UpdateUserInfo(UserDto userInfo) throws Exception;
}
