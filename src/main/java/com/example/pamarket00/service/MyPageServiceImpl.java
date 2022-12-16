package com.example.pamarket00.service;

import com.example.pamarket00.dto.BoardDto;
import com.example.pamarket00.dto.MyPageMainDto;
import com.example.pamarket00.dto.MyPageSellDto;
import com.example.pamarket00.dto.UserDto;
import com.example.pamarket00.mapper.MyPageMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MyPageServiceImpl implements MyPageService{
    @Autowired
    private MyPageMapper myPageMapper;


    @Override
    public Page<MyPageMainDto> MyPageList(int pageNum) throws Exception {

        PageHelper.startPage(pageNum,10);
        return myPageMapper.MyPageList();
    }

    @Override
    public Page<MyPageSellDto> MyPageSellList(int pageNum) throws Exception{

        PageHelper.startPage(pageNum,8);
        return myPageMapper.MyPageSellList();
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

    @Override
    public void insertUserInfo(UserDto userDto) throws Exception {
        myPageMapper.insertUserInfo(userDto);
    }

    @Override
    public int IdCheck(String userId) throws Exception {
        return myPageMapper.idCheck(userId);
    }

    @Override
    public UserDto loginCheck(String userId, String userPw) throws Exception {
        UserDto userDto = myPageMapper.loginCheck(userId, userPw);
        return userDto;
    }



}
