package com.example.pamarket00.controller;


import com.example.pamarket00.dto.BoardDto;
import com.example.pamarket00.dto.MyPageMainDto;
import com.example.pamarket00.dto.UserDto;
import com.example.pamarket00.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class YoungController {
    @Autowired
    private MyPageService myPageService;

    @RequestMapping(value = "/MyPage",method = RequestMethod.GET)
    public ModelAndView MyPage() throws Exception{
        ModelAndView mv = new ModelAndView("YM/MyPage");

        List<MyPageMainDto> boardList = myPageService.MyPageList();
        mv.addObject("boardList",boardList);

        return mv;
    }

    @RequestMapping(value = "/MyPageSell", method = RequestMethod.GET)
    public ModelAndView MyPageSell() throws Exception{
       ModelAndView mv = new ModelAndView("YM/MyPageSell");

       List<BoardDto> sellList = myPageService.MyPageSellList();
       mv.addObject("sellList",sellList);

       return mv;
    }

    @RequestMapping("/MyPageBuy")
    public ModelAndView MyPageBuy() throws Exception{
        ModelAndView mv = new ModelAndView("YM/MyPageBuy");

        List<BoardDto> buyList = myPageService.MyPageBuyList();
        mv.addObject("buyList",buyList);

        return mv;
    }

    @RequestMapping("/MyPageReview")
    public String MypageReview(){
        return "YM/MyPageReview";
    }

    @RequestMapping(value = "/MyPageUserInfo", method = RequestMethod.GET)
    public ModelAndView MyPageUserInfo() throws Exception{
        ModelAndView mv = new ModelAndView("YM/MyPageUserInfo");

        UserDto userInfo = myPageService.userInfo();
        mv.addObject("userInfo",userInfo);

        return mv;
    }

    @RequestMapping("YM/UpdateUserInfo")
    public String UpdateUserInfo(UserDto userInfo) throws Exception{
        myPageService.UpdateUserInfo(userInfo);
        return "redirect:/MyPage";
    }
}
