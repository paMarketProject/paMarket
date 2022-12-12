package com.example.pamarket00.controller;


import com.example.pamarket00.dto.BoardDto;
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
//    private MyPageService myPageService;

    @RequestMapping(value = "/MyPage",method = RequestMethod.GET)
    public String MyPage() throws Exception{
        return "YM/MyPage";
    }

    @RequestMapping("/MyPageSell")
    public ModelAndView MyPageSell() throws Exception{
        ModelAndView mv = new ModelAndView("YM/MyPageSell");

        List<BoardDto> boardList = MyPageService.MyPageSell();
        mv.addObject("boardList",boardList);

        return mv;
    }

    @RequestMapping("/MyPageBuy")
    public String MyPageBuy(){
        return "YM/MyPageBuy";
    }

    @RequestMapping("/MyPageReview")
    public String MypageReview(){
        return "YM/MyPageReview";
    }

    @RequestMapping("/MyPageUpdate")
    public String MyPageUpdate(){
        return "YM/MyPageUpdate";
    }

}
