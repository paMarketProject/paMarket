package com.example.pamarket00.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class YuriController {
//    게시글 목록 페이지
    @RequestMapping("/list")
    public String yuriBoardList() throws Exception {
        return "yuri/boardList";
    }

    @RequestMapping("/write")
    public String yuriBoardWrite() throws Exception {
        return "yuri/boardWrite";
    }
}


