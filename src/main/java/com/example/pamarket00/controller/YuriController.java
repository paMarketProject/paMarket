package com.example.pamarket00.controller;


import com.example.pamarket00.dto.BoardDto;
import com.example.pamarket00.service.YuriBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class YuriController {
    private final YuriBoardService yuriBoardService;
    @Autowired
    public YuriController(YuriBoardService yuriBoardService){
        this.yuriBoardService = yuriBoardService;
    }
//    게시글 목록 페이지
    @RequestMapping(value = "/productList", method = RequestMethod.GET)
    public ModelAndView openProdeuctList() throws Exception{
        ModelAndView mv = new ModelAndView("yuri/yuriBoardList");
        List<BoardDto> dataList = yuriBoardService.selectBoardList();
        mv.addObject("dataList", dataList);

        return mv;
    }

    @RequestMapping("/productWrite")
    public String yuriBoardWrite() throws Exception {
        return "yuriBoardWrite";
    }

    @RequestMapping("/productDetail")
    public String yuriBoardDetail() throws Exception {
        return "yuriBoardDetail";
    }
}


