package com.hk3fg.board.controller;

import com.hk3fg.board.dto.BoardDto;
import com.hk3fg.board.dto.UserDto;
import com.hk3fg.board.service.BoardService;
import com.hk3fg.board.service.LoginService;
import lombok.AllArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;

    private LoginService loginService;


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

    /* 게시글 목록 */
    @GetMapping("/")
    public String list(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {

        String username = get_Uid();

        List<BoardDto> boardList = boardService.getBoardlist(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);

        int max = 0;
        for (int i = 0; i<10; i++){
            if(pageList[i]==null){
                break;
            } else {
                max = pageList[i];
            }
        }
        System.out.println("컨트롤러 리스트 : " + Arrays.toString(pageList) + " max : "+max);

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageList", pageList);
        model.addAttribute("prevBlock",(pageNum-10 <= 0)? 1 : pageNum-10);
        model.addAttribute("nextBlock",(pageNum+10 >= 0)? max-pageNum : pageNum+10);

        model.addAttribute("Login_UID",username);

        return "/board/list";
    }


    /* 게시글 상세 */
    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "board/detail";
    }

    //=====================회원관련==================
    @GetMapping("/member/signUp")
    public String signUpForm(Model model){
        System.out.println("UC에서 들어감");
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
    public String write(Model model) {
        String username = get_Uid();
        model.addAttribute("Login_UID",username);
        System.out.println("BC:여기서 작동-작성 / 정상적 반환");
        return "board/write";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        System.out.println("BC:여기서 작동-저장");
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
