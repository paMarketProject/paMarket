package com.example.pamarket00.service;

import com.example.pamarket00.dto.BoardDto;
import com.example.pamarket00.dto.MyPageMainDto;
import com.example.pamarket00.dto.UserDto;
import com.example.pamarket00.mapper.MyPageMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MyPageServiceImpl implements MyPageService{
    @Autowired
    private MyPageMapper myPageMapper;


    @Override
    public List<MyPageMainDto> MyPageList() throws Exception {

        List<MyPageMainDto> list = null;
        list = myPageMapper.MyPageList();
        return list;
    }

    @Override
    public List<BoardDto> MyPageSellList() throws Exception{
        List<BoardDto> list = null;
        list = myPageMapper.MyPageSellList();
        return list;
    }

    @Override
    public List<BoardDto> MyPageBuyList() throws Exception {
        List<BoardDto> list = null;
        list = myPageMapper.MyPageBuyList();
        return list;
    }

    @Override
    public UserDto userInfo() throws Exception {
        UserDto userInfo = myPageMapper.userInfo();
        return userInfo;
    }

    @Override
    public void UpdateUserInfo(UserDto userInfo) throws Exception {
        myPageMapper.UpdateUserInfo(userInfo);
    }


}
