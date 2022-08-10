package com.hk3fg.board.controller;

import com.hk3fg.board.dto.UserDto;
import com.hk3fg.board.service.LoginService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class UserController {

    private LoginService loginService;

    @GetMapping("/")
    public  String mainPage(){

        return "/";
    }
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
    public String login() {

        return "/member/loginForm";
    }


}
