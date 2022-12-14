package com.example.pamarket00.controller;


import com.example.pamarket00.dto.TownDto;
import com.example.pamarket00.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class townBoardController {
    @Autowired
    private BoardService boardService;

    @RequestMapping("/")
    public String index() throws Exception {
        return "index";
    }

//    @GetMapping("/board/town")
//    public String boardTownList(){
//        return "townboard/boardList";
//    }

//    @GetMapping("/board/town/detaile")
//    public String boardTownDetaile(){
//        return "townboard/boardDetaile";
//    }

    @RequestMapping(value = "/board/town/write", method = RequestMethod.GET)
    public String boardTownWrite() throws Exception{
        return "townboard/boardWrite";
    }

    @RequestMapping(value = "/board/town/write", method = RequestMethod.POST)
    public String insertBoard(TownDto board) throws Exception {
        boardService.insertBoard(board);

        return "redirect:/board/town";
    }

    @RequestMapping(value = "/board/town", method = RequestMethod.GET)
    public ModelAndView openBoardList() throws Exception {
        ModelAndView mv = new ModelAndView("townboard/boardList");

        List<TownDto> boardList = boardService.selectBoardList();
        mv.addObject("boardList", boardList);

        return mv;
    }

    @RequestMapping(value = "/board/town/{idx}", method = RequestMethod.GET)
    public ModelAndView openBoardDetail(@PathVariable("boardNum") int boardNum) throws Exception {
        ModelAndView mv = new ModelAndView("townboard/boardDetail");

        TownDto board = boardService.selectBoardDetail(boardNum);
        mv.addObject("board", board);


        return mv;
    }
}
