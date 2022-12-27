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
import com.example.pamarket00.dto.ReviewDto;
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
    private TownMapper townMapper;

    @Override
    public List<TownDto> selectBoardList() throws Exception {
        return townMapper.selectBoardList();
    }



    @Override
    public void insertBoard(TownDto board) throws Exception {
        townMapper.insertBoard(board);
    }

    @Override
    public void insertReviewBoard(ReviewDto reviewDto,String reviewFromUserId) throws Exception {
        reviewDto.setReviewFromUserId(reviewFromUserId);
        townMapper.insertReviewBoard(reviewDto);
    }

    @Override
    public TownDto selectBoardDetail(int boardNum) throws Exception {
        townMapper.updateTownBoardHitCount(boardNum);
        TownDto townBoard = townMapper.selectBoardDetail(boardNum);

        return townBoard;
    }




    @Override
    public List<CommentDto> selectCommentList(int boardNum) throws Exception {
        return townMapper.selectCommentList(boardNum);
    }

    @Override
    public void commentDelete(int commentBoardNum, int commentNum) throws Exception{
        townMapper.commentDelete(commentNum);
    }

    @Override
    public void insertComment( String commentUserId, String commentContents, int commentBoardNum) throws Exception {
        CommentDto comment = new CommentDto();
        comment.setCommentBoardNum(commentBoardNum);
        comment.setCommentUserId(commentUserId);
        comment.setCommentContents(commentContents);

        townMapper.insertComment(comment);
    }

    @Override
    public Page<TownDto> selectBoardList(int pageNo){
        PageHelper.startPage(pageNo,15);
        return townMapper.selectBoardListPage();
    }


    @Override
    public Page<ReviewDto> selectReviewList(int pageNo, String reviewFromUserId) throws Exception {
        PageHelper.startPage(pageNo,10);
        return townMapper.selectReviewListPage(reviewFromUserId);
    }

    @Override
    public void boardDelete(int boardNum) throws Exception {
        townMapper.boardDelete(boardNum);
    }




    @Override
    public void boardUpdate(String boardTitle, String boardContents, int boardNum) throws Exception {
        TownDto boardUpdate = new TownDto();
        boardUpdate.setBoardTitle(boardTitle);
        boardUpdate.setBoardContents(boardContents);
        boardUpdate.setBoardNum(boardNum);


        townMapper.boardUpdate(boardUpdate);
    }

}

