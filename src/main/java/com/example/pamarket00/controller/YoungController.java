package com.example.pamarket00.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class YoungController {
    @RequestMapping("/MyPage")
    public String MyPage() throws Exception{
        return "YM/MyPage";
    }

    @RequestMapping("/MyPageSell")
    public String MyPageSell(){
        return "YM/MyPageSell";
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
