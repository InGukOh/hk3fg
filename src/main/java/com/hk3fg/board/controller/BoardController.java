package com.hk3fg.board.controller;

import com.hk3fg.board.dto.BoardDto;
import com.hk3fg.board.dto.UserDto;
import com.hk3fg.board.service.BoardService;
import com.hk3fg.board.service.LoginService;
import lombok.AllArgsConstructor;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;

    private LoginService loginService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /*uID 가져오기*/
    public String get_Uid(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
    /*IP앞 두개 가져오기*/
    private String getIp(HttpServletRequest request) {

        String ip = request.getHeader("X-Forwarded-For");
        logger.info(">>>> X-FORWARDED-FOR : " + ip);

        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
            logger.info(">>>> Proxy-Client-IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
            logger.info(">>>> WL-Proxy-Client-IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            logger.info(">>>> HTTP_CLIENT_IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            logger.info(">>>> HTTP_X_FORWARDED_FOR : " + ip);
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        String[] get_ip = ip.split("\\.");
        String view_ip = get_ip[0]+"."+get_ip[1];
        logger.info(">>>> Result : IP Address : "+view_ip);

        return view_ip;

    }

    @GetMapping("/")
    public String index(){

        return "/board/index";
    }

    /* 게시글 목록 */
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {

        String username = get_Uid();

        List<BoardDto> boardList = boardService.getBoardlist(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);
        Integer totalLastPageNum = boardService.totalLastPageNum();

        logger.info("pl : " + Arrays.toString(pageList));
        model.addAttribute("boardList", boardList);
        model.addAttribute("pageList", pageList);
        model.addAttribute("prevBlock",(pageNum-10 <= 0)? 1 : pageNum-10);
        model.addAttribute("nextBlock",(pageNum + 10 > totalLastPageNum)? totalLastPageNum : pageNum+10);
        model.addAttribute("lastBlock",totalLastPageNum);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("Login_UID",username);

        return "/board/list";
    }


    /* 게시글 상세 */
    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model,@RequestParam(value="page", defaultValue = "1") Integer pageNum) {
        String username = get_Uid();
        model.addAttribute("Login_UID",username);
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        model.addAttribute("pageNum", pageNum);
        return "board/detail";
    }

    //=====================회원관련==================
    @GetMapping("/member/signUp")
    public String signUpForm(Model model){
        logger.info("UC에서 들어감");
        model.addAttribute("member",new UserDto());

        return "/member/signUpForm";
    }

    @PostMapping("/member/signUp")
    public String signUp(UserDto userDto){
        loginService.signUp(userDto);

        return "redirect:/";
    }

    @GetMapping("/member/login")
    public String login(Model model) {

        return "/member/loginForm";
    }
    //=====================회원관련==================


    /* 게시글 쓰기 */
    @GetMapping("/post")
    public String write(Model model,HttpServletRequest request) {
        //////////////////////////ip가져오기///////////////////////////

        String ip = getIp(request);

        //////////////////////////ip가져오기///////////////////////////
        String username = get_Uid();
        model.addAttribute("Login_UID",username);
        model.addAttribute("anonymous_IP",ip);
        logger.info("BC:여기서 작동-작성 / 정상적 반환");
        return "board/write";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        logger.info("BC:여기서 작동-저장");

        boardService.savePost(boardDto);

        return "redirect:/";
    }


    /* 게시글 수정 */
    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);
        String username = get_Uid();
        model.addAttribute("Login_UID",username);
        model.addAttribute("boardDto", boardDTO);
        return "board/update";
    }

    @PutMapping("/post/edit/{no}")
    public String update(BoardDto boardDTO) {
        boardService.savePost(boardDTO);

        return "redirect:/";
    }

    /* 게시글 삭제 */
    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return "redirect:/";
    }

    @GetMapping("/board/search")
    public String search(@RequestParam(value="keyword") String keyword, Model model) {
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);

        model.addAttribute("boardList", boardDtoList);

        return "board/list";
    }
}
