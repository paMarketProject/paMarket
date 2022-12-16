package com.example.pamarket00.controller;


import com.example.pamarket00.common.FileUtils;
import com.example.pamarket00.dto.BoardDto;
import com.example.pamarket00.dto.ProductBoardDto;
import com.example.pamarket00.service.YuriBoardService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ModelAndView openProductList(@RequestParam(required = false, defaultValue = "1") int pageNum) throws Exception {
        ModelAndView mv = new ModelAndView("yuri/boardList");

//        PageInfo <ProductBoardDto> dataList = new PageInfo<>(yuriBoardService.selectProductBoardList(pageNum),3);
//        mv.addObject("dataList", dataList);

        PageInfo<ProductBoardDto> dataList = new PageInfo<> (yuriBoardService.selectProductBoardList(pageNum),20);
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
        yuriBoardService.insertProductBoard(board, multipart);
        return "redirect:/productWrite";
    }

//    게시글 수정
    @ResponseBody
    @RequestMapping(value = "/updateProductBoard", method = RequestMethod.POST)
//    public String updateProductBoard(BoardDto board) throws Exception {
    public String updateProductBoard(@RequestBody BoardDto board) throws Exception {
        yuriBoardService.updateProductBoard(board);

        return "redirect:/productWrite";
    }


//      게시글 삭제

    @RequestMapping("/deleteProductBoard")
    @ResponseBody
    public String deleteProductBoard(@RequestParam("boardNum") int boardNum) throws Exception {
        yuriBoardService.deleteProductBoard(boardNum);
        return "redirect:yuri/productList";
    }

//    상세페이지
    @RequestMapping("/productDetail")
    public ModelAndView openProductBoardDetail(@RequestParam(value = "boardNum")int boardNum) throws Exception {
        ModelAndView mv = new ModelAndView("yuri/boardDetail");
        ProductBoardDto board = yuriBoardService.selectProductBoardDetail(boardNum);
        mv.addObject("board",board);

        return mv;
    }
}


