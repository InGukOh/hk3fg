package com.hk3fg.board.controller;

import com.hk3fg.board.dto.BoardDto;

import com.hk3fg.board.service.BoardService;
import com.hk3fg.board.service.LoginService;
import lombok.AllArgsConstructor;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

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

    @GetMapping("/")
    public String MainPage(){
        logger.info("BoardController : MainPage / Action : MainPage OPEN | Activate");
        return "/board/index";
    }

    /* 게시글 목록 */
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
        logger.info("BoardController : list / Action : list OPEN | start");
        String username = InfoController.get_Uid();

        List<BoardDto> boardList = boardService.getBoardlist(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);
        Integer totalLastPageNum = boardService.totalLastPageNum();

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageList", pageList);
        model.addAttribute("prevBlock",(pageNum-10 <= 0)? 1 : pageNum-10);
        model.addAttribute("nextBlock",(pageNum + 10 > totalLastPageNum)? totalLastPageNum : pageNum+10);
        model.addAttribute("lastBlock",totalLastPageNum);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("Login_UID",username);

        logger.info("BoardController : list / Action : list OPEN | end\n");

        return "/board/list";
    }

    /* 게시글 상세 */
    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model,@RequestParam(value="page", defaultValue = "1") Integer pageNum) {
        logger.info("BoardController : detail / Action : get Data(게시글) & get UID | start");

        String username = InfoController.get_Uid();
        list(model,pageNum);
        model.addAttribute("Login_UID",username);
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        model.addAttribute("pageNum", pageNum);

        logger.info("BoardController : detail / Action : get Data(게시글) & get UID | end\n");
        return "board/detail";
    }


    /* 게시글 쓰기 */
    @GetMapping("/post")
    public String write(Model model,HttpServletRequest request) {
        logger.info("BoardController : write / Action : post OPEN | start");
        //////////////////////////ip가져오기///////////////////////////

        String ip = InfoController.getIp(request);

        //////////////////////////ip가져오기///////////////////////////
        String username = InfoController.get_Uid();

        model.addAttribute("Login_UID",username);
        model.addAttribute("anonymous_IP",ip);

        logger.info("BoardController : write / Action : post OPEN | end\n");
        return "board/write";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        logger.info("BoardController : write / Action : post SAVE | start");

        boardService.savePost(boardDto);

        logger.info("BoardController : write / Action : post SAVE | end\n");

        return "/board/list";
    }


    /* 게시글 수정 */
    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        logger.info("BoardController : edit / Action : getting Entity & insert Entity | start");
        BoardDto boardDTO = boardService.getPost(no);
        String username = InfoController.get_Uid();

        model.addAttribute("Login_UID",username);
        model.addAttribute("boardDto", boardDTO);
        logger.info("BoardController : edit / Action : getting Entity & insert Entity | end\n");
        return "board/update";
    }

    @PutMapping("/post/edit/{no}")
    public String update(BoardDto boardDTO) {
        logger.info("BoardController : update / Action : save Entity | start");

        boardService.savePost(boardDTO);

        logger.info("BoardController : update / Action : save Entity | end\n");
        return "redirect:/board/list";
    }

    /* 게시글 삭제 */
    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        logger.info("BoardController : delete / Action : delete Entity | start");

        boardService.deletePost(no);

        logger.info("BoardController : delete / Action : delete Entity | end\n");
        return "board/list";
    }

    @GetMapping("/board/search")
    public String search(@RequestParam(value="keyword") String keyword, Model model) {
        logger.info("BoardController : search / Action : search Entity | start");

        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);

        model.addAttribute("boardList", boardDtoList);

        logger.info("BoardController : search / Action : search Entity | end\n");
        return "board/list";
    }
}
