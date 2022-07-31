package com.hk3fg.prj.Controller;

import com.hk3fg.prj.domain.Board;
import com.hk3fg.prj.service.BoardService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.print.Pageable;

@Controller // 어노테이션을 입력하면 알아서 상단에 import 가 된다!

@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    @GetMapping("/hello")
    public String Hello() {
        return "/boards/hello";
    }

    @GetMapping("/test")
    public String test(Model model){
        model.addAttribute("cnt",service.boardCount());
        model.addAttribute("test",service.boardList());

        return "/boards/hello";
    }
    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("list", service.boardList());
        return "/boards/main";
    }

    @GetMapping("/view")
    public String viewBoard(Model model, Long num) {
        service.viewCount(num); // 추가
        model.addAttribute("halo", service.getBoard(num));

        return "/boards/view";
    }

    @GetMapping("/upload")
    public String uploadBoardForm() {
        return "/boards/upload";
    }

    @PostMapping("/upload")
    public String uploadBoard(Board board) {
        service.uploadBoard(board);
        return "redirect:/board/main"; // 디렉토리가 아니라 주소라 board 임.
    }
    @GetMapping("/update")
    public String updateBoardForm(Model model, Long num) {
        model.addAttribute("update", service.getBoard(num));

        return "/boards/update";
    }

    @PostMapping("/update")
    public String updateBoard(Board board) {
        service.updateBoard(board);
        return "redirect:/board/main";
    }

    @GetMapping("/delete")
    public String deleteBoard(Long num) {
        service.deleteBoard(num);
        return "redirect:/board/main";
    }


}