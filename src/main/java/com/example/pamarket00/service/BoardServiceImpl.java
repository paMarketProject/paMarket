package com.example.pamarket00.service;//package com.example.newboard.service;
//
//import com.bitc.board.service.BoardService;
//import com.example.newboard.dto.BoardDto;
//import com.example.newboard.mapper.BoardMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class BoardServiceImpl implements BoardService {
//
//@Autowired
//private BoardMapper boardMapper;
//@Override
//    public List<BoardDto> selectBoardList() throws Exception {
//        return boardMapper.selectBoardList();
//    }
//}
import com.example.pamarket00.dto.CommentDto;
import com.example.pamarket00.dto.TownDto;
import com.example.pamarket00.mapper.TownMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private TownMapper TownMapper;

    @Override
    public List<TownDto> selectBoardList() throws Exception {
        return TownMapper.selectBoardList();
    }


    @Override
    public List<TownDto> selectReviewList() throws Exception {
        return TownMapper.selectReviewList();
    }



    @Override
    public void insertBoard(TownDto board) throws Exception {
        TownMapper.insertBoard(board);
    }

    @Override
    public TownDto selectBoardDetail(int boardNum) throws Exception {
        TownMapper.updateTownBoardHitCount(boardNum);
        TownDto townBoard = TownMapper.selectBoardDetail(boardNum);

        return townBoard;
    }




    @Override
    public List<CommentDto> selectCommentList(int boardNum) throws Exception {
        return TownMapper.selectCommentList(boardNum);
    }

    @Override
    public void commentDelete(int commentBoardNum, int commentNum) throws Exception{
        TownMapper.commentDelete(commentNum);
    }

    @Override
    public void insertComment( String commentUserId, String commentContents, int commentBoardNum) throws Exception {
        CommentDto comment = new CommentDto();
        comment.setCommentBoardNum(commentBoardNum);
        comment.setCommentUserId(commentUserId);
        comment.setCommentContents(commentContents);

        TownMapper.insertComment(comment);
    }

    @Override
    public Page<TownDto> selectBoardList(int pageNo){
        PageHelper.startPage(pageNo,15);
        return TownMapper.selectBoardListPage();
    }


    @Override
    public Page<TownDto> selectReviewList(int pageNo) throws Exception {
        PageHelper.startPage(pageNo,15);
        return TownMapper.selectReviewListPage();
    }

    @Override
    public void boardDelete(int boardNum) throws Exception {
        TownMapper.boardDelete(boardNum);
    }




    @Override
    public void boardUpdate(String boardTitle, String boardContents, int boardNum) throws Exception {
        TownDto boardUpdate = new TownDto();
        boardUpdate.setBoardTitle(boardTitle);
        boardUpdate.setBoardContents(boardContents);
        boardUpdate.setBoardNum(boardNum);


        TownMapper.boardUpdate(boardUpdate);
    }

}

