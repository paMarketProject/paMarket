package com.example.pamarket00.controller;


import com.example.pamarket00.dto.BoardDto;
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
    private final YuriBoardService yuriBoardService;
    @Autowired
    public YuriController(YuriBoardService yuriBoardService){
        this.yuriBoardService = yuriBoardService;
    }
//    게시글 목록 페이지
    @RequestMapping(value = "/productList", method = RequestMethod.GET)
    public ModelAndView openProdeuctList() throws Exception{
        ModelAndView mv = new ModelAndView("yuri/test");
        List<BoardDto> dataList = yuriBoardService.selectBoardList();
        mv.addObject("dataList", dataList);

        return mv;
    }

    @RequestMapping(value = "/productWrite", method = RequestMethod.GET)
    public String insertBoard(BoardDto board, MultipartHttpServletRequest multipart) throws Exception {
//    업로드된 파일 데이터를 서비스 영역에서 처리하기 위해서 매개변수를 추가
//        YuriBoardService.insertProductBoard(board, multipart);

        return "redirect:/board/openBoardList";
    }


    @RequestMapping("/productDetail")
    public String yuriBoardDetail() throws Exception {
        return "yuri/yuriBoardDetail";
    }
}


