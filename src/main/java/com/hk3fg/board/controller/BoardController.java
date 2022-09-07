package com.hk3fg.board.controller;

import com.hk3fg.board.dto.BoardDto;
import com.hk3fg.board.dto.CommentDto;
import com.hk3fg.board.service.BoardService;

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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public String MainPage(Model model){
        logger.info("BoardController : MainPage / Action : MainPage OPEN | Activate");
        String username = InfoController.get_Uid();
        model.addAttribute("Login_uID",username);
        return "board/index";
    }

    /* 게시글 목록 */
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
        logger.info("BoardController : list / Action : list OPEN | start");
        String Method_Name = "list";
        String username = InfoController.get_Uid();

        List<BoardDto> boardList = boardService.getBoardlist(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum,Method_Name,"");
        Integer totalLastPageNum = boardService.totalLastPageNum(Method_Name,"");

        logger.info("pageList : " + Arrays.toString(Arrays.stream(pageList).filter(i -> i != null).toArray()));
        model.addAttribute("boardList", boardList);
        model.addAttribute("pageList", Arrays.stream(pageList).filter(i -> i != null).toArray());
        model.addAttribute("prevBlock",(pageNum-10 <= 0)? 1 : pageNum-10);
        model.addAttribute("nextBlock",(pageNum + 10 > totalLastPageNum)? totalLastPageNum : pageNum+10);
        model.addAttribute("lastBlock",totalLastPageNum);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("Login_uID",username);

        logger.info("BoardController : list / Action : list OPEN | end\n");

        return "/board/list";
    }

    /* 게시글 상세 */
    @GetMapping("/post/{no}")
    public String detail(Model model,
                         HttpServletRequest request,
                         @PathVariable("no") Long id,
                         @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
        logger.info("BoardController : detail / Action : get Data(게시글) & get uID | start");

        String username = InfoController.get_Uid();
        String ip = InfoController.getIp(request);
        BoardDto boardDTO = boardService.getPost(id);
        List<CommentDto> commentList = boardService.getPost_Comment(id);

        list(model,pageNum);

        model.addAttribute("Login_uID",username);
        model.addAttribute("anonymous_IP",ip);
        model.addAttribute("boardDto", boardDTO);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("commentList",commentList);

        logger.info("BoardController : detail / Action : get Data(게시글) & get uID | end\n");
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

        list(model,1);
        model.addAttribute("pageNum", 1);

        model.addAttribute("Login_uID",username);
        model.addAttribute("anonymous_IP",ip);

        logger.info("BoardController : write / Action : post OPEN | end\n");
        return "board/write";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        logger.info("BoardController : write / Action : post SAVE | start");

        boardService.savePost_Write(boardDto);

        logger.info("BoardController : write / Action : post SAVE | end\n");

        return "redirect:/list/?page=1";
    }


    /* 게시글 수정 */
    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        logger.info("BoardController : edit / Action : getting Entity & insert Entity | start");
        BoardDto boardDTO = boardService.getPost(no);

        String username = InfoController.get_Uid();

        list(model,1);

        model.addAttribute("pageNum", 1);

        logger.info("boardDTo: " + boardDTO);

        model.addAttribute("Login_uID",username);
        model.addAttribute("boardDto", boardDTO);

        logger.info("BoardController : edit / Action : getting Entity & insert Entity | end\n");
        return "board/update";
    }

    @PutMapping("/post/edit/{no}")
    /*@RequestMapping(value="/post/edit/{no}", method = {RequestMethod.GET})*/
    public String update(BoardDto boardDTO) {
        logger.info("BoardController : update / Action : save Entity | start");

        boardService.savePost_Write(boardDTO);

        logger.info("BoardController : update / Action : save Entity | end\n");
        return "redirect:/list/?page=1";
    }

    /* 게시글 삭제 */
    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        logger.info("BoardController : delete / Action : delete Entity | start");

        boardService.deletePost(no);

        logger.info("BoardController : delete / Action : delete Entity | end\n");
        return "board/list";
    }
    /* 게시글 검색 */
    @GetMapping("/board/search")
    public String search(@RequestParam(value="keyword") String keyword, @RequestParam(value="page", defaultValue = "1") Integer pageNum, Model model) {
        logger.info("BoardController : search / Action : search Entity | start");
        String Method_Name = "search";
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);
        String username = InfoController.get_Uid();
        Integer[] pageList = boardService.getPageList(pageNum,Method_Name,keyword);
        Integer totalLastPageNum = boardService.totalLastPageNum(Method_Name,keyword);

        model.addAttribute("Login_uID",username);
        model.addAttribute("boardList", boardDtoList);
        model.addAttribute("pageList", Arrays.stream(pageList).filter(i -> i != null).toArray());
        model.addAttribute("prevBlock",(pageNum-10 <= 0)? 1 : pageNum-10);
        model.addAttribute("nextBlock",(pageNum + 10 > totalLastPageNum)? totalLastPageNum : pageNum+10);
        model.addAttribute("lastBlock",totalLastPageNum);
        model.addAttribute("pageNum",pageNum);

        logger.info("BoardController : search / Action : search Entity | end\n");
        return "board/list";
    }

    /* !!!!!!!!!!! 댓글 기능 관련 !!!!!!!!!!! */
    @PostMapping("/post_comment")
    public String comment(CommentDto commentDto,HttpServletRequest request) {
        logger.info("BoardController : comment / Action : post_comment SAVE | start");

        boardService.savePost_Comment(commentDto);

        logger.info("BoardController : comment / Action : post_comment SAVE | end\n");
        String referer = request.getHeader("Referer");
        int index = referer.indexOf("post");
        referer = referer.substring(index,referer.length());

        logger.info(("REFERER : " + referer));

        return "redirect:/"+referer;
    }


}
