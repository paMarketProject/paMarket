package com.example.pamarket00.controller;


import com.example.pamarket00.common.FileUtils;
import com.example.pamarket00.dto.BoardDto;
import com.example.pamarket00.dto.ProductBoardDto;
import com.example.pamarket00.dto.UserDto;
import com.example.pamarket00.service.YuriBoardService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class YuriController {

    @Autowired
    YuriBoardService yuriBoardService;
    @Autowired
    FileUtils fileUtils;
//    게시글 목록 페이지

    @RequestMapping("/chatting")
    public String index() throws Exception {
        return "yuri/chatting";
    }

    @RequestMapping(value = "/productList", method = RequestMethod.GET)
    public ModelAndView openProductList(@RequestParam(required = false, defaultValue = "1") int pageNum) throws Exception {
        ModelAndView mv = new ModelAndView("yuri/boardList");

//        PageInfo <ProductBoardDto> dataList = new PageInfo<>(yuriBoardService.selectProductBoardList(pageNum),3);
//        mv.addObject("dataList", dataList);

        PageInfo<ProductBoardDto> dataList = new PageInfo<>(yuriBoardService.selectProductBoardList(pageNum), 20);
        mv.addObject("dataList", dataList);

        return mv;
    }

    //    검색기능 진행 중
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(@RequestParam(required = false, defaultValue = "1")int pageNum, @RequestParam("searchText")String searchText) throws Exception {
        ModelAndView mv = new ModelAndView("yuri/search");

        PageInfo<ProductBoardDto> dataList = new PageInfo<>(yuriBoardService.search(pageNum,searchText));
        mv.addObject("dataList", dataList);
        return mv;
    }


    // 게시물 등록 view 페이지
    @RequestMapping("/productWrite")
    public String yuriBoardWrite() throws Exception {
        return "yuri/boardWrite";
    }

    @RequestMapping(value = "/insertProductBoard", method = RequestMethod.POST)
    public String insertProductBoard(BoardDto board, MultipartHttpServletRequest multipart) throws Exception {
        yuriBoardService.insertProductBoard(board, multipart);
        return "redirect:/productList";
    }


    //    게시글 수정
    @ResponseBody
    @RequestMapping(value = "/updateProductBoard", method = RequestMethod.POST)
//    public String updateProductBoard(BoardDto board) throws Exception {
    public String updateProductBoard(@RequestParam("boardNum") int boardNum, @RequestParam("boardTitle") String boardTitle, @RequestParam("boardContents") String boardContents, @RequestParam("boardProState") int boardProState) throws Exception {
        yuriBoardService.updateProductBoard(boardNum, boardTitle, boardContents, boardProState);

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
    public ModelAndView openProductBoardDetail(@RequestParam(value = "boardNum") int boardNum) throws Exception {
        ModelAndView mv = new ModelAndView("yuri/boardDetail");
        ProductBoardDto board = yuriBoardService.selectProductBoardDetail(boardNum);
        mv.addObject("board", board);

        return mv;
    }

    @RequestMapping("/locationCheck")
    public String locationCheck() throws Exception {
        return "yuri/locationCheck";
    }

//    지역인증하기
    @ResponseBody
    @RequestMapping(value = "/locationBtnCheck",method = RequestMethod.POST)
    public String locationBtnCheck(@RequestParam(value = "userId") String userId, @RequestParam(value = "userCheck") int userCheck) throws Exception {
        yuriBoardService.locationBtnCheck(userId, userCheck);
        return "redirect:/myPage";
    }


}



