package com.example.pamarket00.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class YuriController {
//    게시글 목록 페이지
    @RequestMapping("/list")
    public String boardList() throws Exception {
        return "yuri/boardList";
    }
}


