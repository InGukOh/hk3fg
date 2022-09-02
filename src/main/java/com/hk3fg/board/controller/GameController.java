package com.hk3fg.board.controller;

import com.hk3fg.board.dto.BoardDto;
import com.hk3fg.board.dto.GameDto;
import com.hk3fg.board.service.GameService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class GameController {

    private GameService gameService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/game_BungterMelon")
    public String GamePage(Model model){
        logger.info("GameController : GamePage / Action : GamePage OPEN | Activate");
        String username = InfoController.get_Uid();
        model.addAttribute("Login_UID", username);
        if (username.equals("anonymousUser")) return "/member/loginForm";
        return "/game/game_BungterMelon";
    }
    @PostMapping("/post_gameScore")
    public String Score(GameDto gameDto) {
        logger.info("BoardController : write / Action : post SAVE | start");

        gameService.saveScore(gameDto);

        logger.info("BoardController : write / Action : post SAVE | end\n");

        return "redirect:/game_BungterMelon";
    }
}
