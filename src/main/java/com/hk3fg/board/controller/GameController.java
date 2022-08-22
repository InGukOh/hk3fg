package com.hk3fg.board.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class GameController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/game")
    public String GamePage(Model model){
        logger.info("GameController : GamePage / Action : GamePage OPEN | Activate");
        String username = InfoController.get_Uid();
        model.addAttribute("Login_UID", username);
        if (username.equals("anonymousUser")) return "/member/loginForm";
        return "/game/game";
    }
}
