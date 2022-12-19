package com.example.pamarket00.service;
import com.example.pamarket00.dto.BoardDto;
import com.example.pamarket00.dto.MyPageMainDto;
import com.example.pamarket00.dto.MyPageSellDto;
import com.example.pamarket00.dto.UserDto;
import com.github.pagehelper.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
public interface MyPageService {

    Page<MyPageMainDto> MyPageList(int pageNum) throws Exception;

    Page<MyPageSellDto> MyPageSellList(int pageNum) throws Exception;

    List<BoardDto> MyPageBuyList() throws Exception;


    void UpdateUserInfo(UserDto userInfo) throws Exception;

    void insertUserInfo(UserDto userDto) throws Exception;

    int IdCheck(String userId) throws Exception;


    UserDto loginCheck(String userId, String userPw) throws Exception;

}
