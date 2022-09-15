package com.hk3fg.board.controller;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;

@Controller
@Slf4j

public class CustomErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));

        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());

            logger.info("httpStatus : " + statusCode);

            // 404 error
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("code", status.toString());
                model.addAttribute("msg","페이지가 존재하지 않습니다.");
                model.addAttribute("timestamp", new Date());
            }

            // 500 error
            if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("code", status.toString());
                model.addAttribute("msg", "서비스 장애 / 잠시후 다시 시도해주세요.");
                model.addAttribute("timestamp", new Date());
            }
        }

        return "error/error";
    }
}
