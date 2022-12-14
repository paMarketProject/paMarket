package com.example.pamarket00.controller;


import com.example.pamarket00.common.FileUtils;
import com.example.pamarket00.dto.BoardDto;
import com.example.pamarket00.dto.ProductBoardDto;
import com.example.pamarket00.service.YuriBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class YuriController {

    @Autowired
    YuriBoardService yuriBoardService;
    @Autowired
    FileUtils fileUtils;
//    게시글 목록 페이지

    @RequestMapping(value = "/productList", method = RequestMethod.GET)
    public ModelAndView openProductList() throws Exception{
        ModelAndView mv = new ModelAndView("yuri/boardList");
        List<ProductBoardDto> dataList = yuriBoardService.selectProductBoardList();
        mv.addObject("dataList", dataList);

        return mv;
    }
// 게시물 등록 view 페이지
    @RequestMapping("/productWrite")
    public String yuriBoardWrite() throws Exception {
        return "yuri/boardWrite";
    }

    @RequestMapping("/insertProductBoard")
    public String insertProductBoard(BoardDto board, MultipartHttpServletRequest multipart) throws Exception {
        yuriBoardService.insertProductBoard(board,multipart);
        return "redirect:/productWrite";
    }

    @RequestMapping("/updateProductBoard")
    public String updateProductBoard(BoardDto board) throws Exception {
        yuriBoardService.updateProductBoard(board);

        return "redirect:/productWrite";

    }

    @RequestMapping("/detail")
    public String yuriBoardDetail() throws Exception {
        return "yuri/boardDetail";
    }
}


