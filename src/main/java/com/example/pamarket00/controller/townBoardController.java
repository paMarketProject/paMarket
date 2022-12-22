package com.example.pamarket00.controller;


import com.example.pamarket00.dto.CommentDto;
import com.example.pamarket00.dto.TownDto;
import com.example.pamarket00.service.BoardService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/boardTownWrite", method = RequestMethod.GET)
    public String boardTownWrite() throws Exception{
        return "townboard/boardWrite";
    }

    @RequestMapping(value = "/boardTownWrite", method = RequestMethod.POST)
    public String insertBoard(TownDto board) throws Exception {
        boardService.insertBoard(board);

        return "redirect:/boardTown";
    }

    @RequestMapping(value = "/boardTown", method = RequestMethod.GET)
    public ModelAndView openBoardList(@RequestParam(required = false, defaultValue = "1") int pageNum) throws Exception {
        ModelAndView mv = new ModelAndView("townboard/boardList");

        PageInfo<TownDto> boardList = new PageInfo<>(boardService.selectBoardList(pageNum),5);
        mv.addObject("boardList", boardList);

        return mv;
    }

    @RequestMapping(value = "/boardTown{boardNum}", method = RequestMethod.GET)
    public ModelAndView openBoardDetail(@PathVariable("boardNum") int boardNum) throws Exception {
        ModelAndView mv = new ModelAndView("townboard/boardDetaile");

        TownDto board = boardService.selectBoardDetail(boardNum);
        List<CommentDto> commentList = boardService.selectCommentList(boardNum);
        mv.addObject("board", board);
        mv.addObject("commentList", commentList);

        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/boardTownReplyWrite" , method = RequestMethod.POST)
    public Object commentWrite(
            @RequestParam("commentContents") String commentContents,
            @RequestParam("commentUserId") String commentUserId,
            @RequestParam("commentBoardNum") int commentBoardNum) throws Exception {


        boardService.insertComment(commentUserId, commentContents, commentBoardNum);
        List<CommentDto> commentList = boardService.selectCommentList(commentBoardNum);

        return commentList;
    }


    @ResponseBody
    @RequestMapping(value = "/boardTownCommentDelete", method = RequestMethod.POST)
    public String commentDelete(
            @RequestParam("commentNum") int commentNum,
            @RequestParam("commentBoardNum") int commentBoardNum) throws Exception {
        boardService.commentDelete(commentBoardNum, commentNum);
        return "redirect:/boardTown{commentBoardNum}";
    }

    @ResponseBody
    @RequestMapping(value =  "/boardTownDelete", method = RequestMethod.POST)
    public String boardDelete(
            @RequestParam("boardNum") int boardNum) throws Exception {
        boardService.boardDelete(boardNum);
        return "redirect:/boardTown";
    }

    @ResponseBody
    @RequestMapping(value =  "/boardTownUpdate", method = RequestMethod.POST)
    public String boardUpdate(
            @RequestParam("boardTitle") String boardTitle,
            @RequestParam("boardContents") String boardContents,
            @RequestParam("boardNum") int boardNum) throws Exception {
        boardService.boardUpdate(boardTitle,boardContents,boardNum);
        return "redirect:/boardTown";
    }


//    @ResponseBody
//    @RequestMapping(value = "/board/town/rereplywrite" , method = RequestMethod.POST)
//    public Object cocommentWrite(
//            @RequestParam("commentContents") String commentContents,
//            @RequestParam("commentUserId") String commentUserId,
//            @RequestParam("commentBoardNum") int commentBoardNum) throws Exception {
//
//        List<CommentDto> commentList = boardService.selectCommentList(commentBoardNum);
//        boardService.insertComment(commentUserId, commentContents, commentBoardNum);
//        CommentDto cocoment = boardService.selectCocoment(commentNum);
//        cocoment.setCommentContents(">> " + cocoment.getCommentContents());
//        commentList.
//
//
//        return commentList;
//    }




}
