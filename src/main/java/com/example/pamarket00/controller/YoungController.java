package com.example.pamarket00.controller;


import com.example.pamarket00.dto.*;
import com.example.pamarket00.service.BoardService;
import com.example.pamarket00.service.MyPageService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class YoungController {
    @Autowired
    private MyPageService myPageService;

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/myPage",method = RequestMethod.GET)
    public ModelAndView MyPage(@RequestParam(required = false, defaultValue = "1") int pageNum, HttpServletRequest request) throws Exception{
        ModelAndView mv = new ModelAndView("YM/myPage");


        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute("user");
        session.setMaxInactiveInterval(1800);

        PageInfo<MyPageMainDto> boardList = new PageInfo<>(myPageService.MyPageList(pageNum,user.getUserId()),20);
        mv.addObject("boardList",boardList);

        return mv;
    }

    @RequestMapping(value = "/myPageSell", method = RequestMethod.GET)
    public ModelAndView MyPageSell(@RequestParam(required = false, defaultValue = "1") int pageNum, HttpServletRequest request) throws Exception{
       ModelAndView mv = new ModelAndView("YM/myPageSell");

       HttpSession session = request.getSession();
       UserDto user = (UserDto) session.getAttribute("user");
       session.setMaxInactiveInterval(1800);


       PageInfo<MyPageSellDto> sellList = new PageInfo<>(myPageService.MyPageSellList(pageNum,user.getUserId()),20);
       mv.addObject("sellList",sellList);

       return mv;
    }

    @RequestMapping("/myPageBuy")
    public ModelAndView MyPageBuy() throws Exception{
        ModelAndView mv = new ModelAndView("YM/myPageBuy");

        List<BoardDto> buyList = myPageService.MyPageBuyList();
        mv.addObject("buyList",buyList);

        return mv;
    }

    @RequestMapping("/myPageReview")
    public String MypageReview(){
        return "YM/myPageReview";
    }

    @RequestMapping(value = "/myPageUserInfo", method = RequestMethod.GET)
    public String MyPageUserInfo() throws Exception{
        return "YM/myPageUserInfo";
    }

    @RequestMapping(value = "updateUserInfo")
    public String UpdateUserInfo(UserDto userInfo, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        myPageService.UpdateUserInfo(userInfo);

        if(session.getAttribute("user") != null){
            UserDto user = myPageService.newSession(userInfo);
            session.setAttribute("user",user);
        }
        return "redirect:/myPage";
    }

    @RequestMapping("/member")
    public String Member() throws Exception{
        return "YM/member";
    }

    @RequestMapping("join")
    public String Join(UserDto userDto) throws Exception{
        myPageService.insertUserInfo(userDto);
        return "redirect:/productList";
    }

    @ResponseBody
    @RequestMapping("idCheck")
    public int IdCheck(String userId) throws Exception{
         int result = myPageService.IdCheck(userId);
         return result;
    }

    @RequestMapping("login")
    public String login(){
        return "YM/login";
    }

    @PostMapping("/loginCheck")
    @ResponseBody
    public Object loginIdCheck(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        UserDto userDto = myPageService.loginCheck(userId, userPw);


        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }

        session.setAttribute("user", userDto);
        session.setMaxInactiveInterval(1800);

        if (userDto == null) {
            return 0;
        }
        else {
            return userDto;
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) throws Exception{
        session.removeAttribute("user");

        return "redirect:productList";
    }

// seob이 추가한 부분. 타운컨트롤러에서 가져 옴
    @RequestMapping(value = "/myPageReview", method = RequestMethod.GET)
    public ModelAndView openBoardList(@RequestParam(required = false, defaultValue = "1") int pageNum) throws Exception {
        ModelAndView mv = new ModelAndView("YM/myPageReview");

        PageInfo<TownDto> boardList = new PageInfo<>(boardService.selectReviewList(pageNum),5);
        mv.addObject("boardList", boardList);

        return mv;
    }


}
