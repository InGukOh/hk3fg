package com.hk3fg.board.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
@AllArgsConstructor
public class InfoController {

    private static Logger logger = LoggerFactory.getLogger(BoardController.class);

    //////////////////////// /사용자 정보 //////////////////////////
    /*uID 가져오기*/
    public static String get_Uid(){
        logger.info("|||||||| GETTING UID ||||||||");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        logger.info("|||||||| GETTING UID ||||||||");
        return username;
    }
    /*IP앞 두개 가져오기*/
    public static String getIp(HttpServletRequest request) {

        logger.info("|||||||| GETTING IP ||||||||");
        String ip = request.getHeader("X-Forwarded-For");
        logger.info(">>>> X-FORWARDED-FOR : " + ip);

        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
            logger.info(">>>> Proxy-Client-IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
            logger.info(">>>> WL-Proxy-Client-IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            logger.info(">>>> HTTP_CLIENT_IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            logger.info(">>>> HTTP_X_FORWARDED_FOR : " + ip);
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        logger.info("|||||||| GETTING IP ||||||||\n");
        logger.info("%n");

        String[] get_ip = ip.split("\\.");

        String view_ip = get_ip[0]+"."+get_ip[1];
        logger.info("!!!!! Result : IP Address : "+view_ip+"!!!!!\n");

        return view_ip;

    }
//////////////////////// /사용자 정보 //////////////////////////

}
