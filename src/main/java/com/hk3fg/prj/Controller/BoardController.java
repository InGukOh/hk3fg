package com.hk3fg.prj.Controller;

import com.hk3fg.prj.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}