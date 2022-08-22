package com.hk3fg.board.controller;

import com.hk3fg.board.dto.UserDto;
import com.hk3fg.board.service.LoginService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
public class UserController {
    private LoginService loginService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/member/signUp")
    public String signUpForm(Model model){
        logger.info("UserController : signUpForm / Action : signUpForm OPEN | start");

        model.addAttribute("member",new UserDto());

        logger.info("UserController : signUpForm / Action : signUpForm OPEN  | end\n");
        return "/member/signUpForm";
    }

    @PostMapping("/member/signUp")
    public String signUp(UserDto userDto){
        logger.info("UserController : signUp / Action : Add UserDATA to DB | start");

        loginService.signUp(userDto);

        logger.info("UserController : signUp / Action : Add UserDATA to DB | end\n");
        return "redirect:/";
    }

    @RequestMapping(value = "/member/login", method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest request) {
        logger.info("UserController : login / Action : Login Action | start");

        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("prevPage", referrer);

        logger.info("move Location : " + referrer);

        logger.info("UserController : login / Action : Login Action | end\n");
        return "/member/loginForm";
    }
}
