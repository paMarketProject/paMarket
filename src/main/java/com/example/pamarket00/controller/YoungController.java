package com.example.pamarket00.controller;


import com.example.pamarket00.dto.BoardDto;
import com.example.pamarket00.dto.MyPageMainDto;
import com.example.pamarket00.dto.MyPageSellDto;
import com.example.pamarket00.dto.UserDto;
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

    @RequestMapping(value = "/MyPage",method = RequestMethod.GET)
    public ModelAndView MyPage(@RequestParam(required = false, defaultValue = "1") int pageNum, HttpServletRequest request) throws Exception{
        ModelAndView mv = new ModelAndView("YM/MyPage");


        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute("user");
        session.setMaxInactiveInterval(1800);

        PageInfo<MyPageMainDto> boardList = new PageInfo<>(myPageService.MyPageList(pageNum,user.getUserId()),20);
        mv.addObject("boardList",boardList);

        return mv;
    }

    @RequestMapping(value = "/MyPageSell", method = RequestMethod.GET)
    public ModelAndView MyPageSell(@RequestParam(required = false, defaultValue = "1") int pageNum, HttpServletRequest request) throws Exception{
       ModelAndView mv = new ModelAndView("YM/MyPageSell");

       HttpSession session = request.getSession();
       UserDto user = (UserDto) session.getAttribute("user");
       session.setMaxInactiveInterval(1800);


       PageInfo<MyPageSellDto> sellList = new PageInfo<>(myPageService.MyPageSellList(pageNum,user.getUserId()),20);
       mv.addObject("sellList",sellList);

       return mv;
    }

    @RequestMapping("/MyPageBuy")
    public ModelAndView MyPageBuy() throws Exception{
        ModelAndView mv = new ModelAndView("YM/MyPageBuy");

        List<BoardDto> buyList = myPageService.MyPageBuyList();
        mv.addObject("buyList",buyList);

        return mv;
    }

    @RequestMapping("/MyPageReview")
    public String MypageReview(){
        return "YM/MyPageReview";
    }

    @RequestMapping(value = "/MyPageUserInfo", method = RequestMethod.GET)
    public String MyPageUserInfo() throws Exception{
        return "YM/MyPageUserInfo";
    }

    @RequestMapping(value = "/YM/UpdateUserInfo")
    public String UpdateUserInfo(UserDto userInfo, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        myPageService.UpdateUserInfo(userInfo);

        if(session.getAttribute("user") != null){
            UserDto user = myPageService.newSession(userInfo);
            session.setAttribute("user",user);
        }
        return "redirect:/MyPage";
    }

    @RequestMapping("/Member")
    public String Member() throws Exception{
        return "YM/Member";
    }
    @RequestMapping("YM/Join")
    public String Join(UserDto userDto) throws Exception{
        myPageService.insertUserInfo(userDto);
        return "redirect:/Member";
    }
    @ResponseBody
    @RequestMapping("IdCheck")
    public int IdCheck(String userId) throws Exception{
         int result = myPageService.IdCheck(userId);
         return result;
    }

    @RequestMapping("Login")
    public String LoginCheck(){
        return "YM/Login";
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

        if (userDto == null) {
            return 0;
        }
        else {
            return userDto;
        }
    }

}
