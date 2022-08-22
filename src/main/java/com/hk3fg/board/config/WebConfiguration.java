package com.hk3fg.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /* '/js/**'로 호출하는 자원은 '/static/js/' 폴더 아래에서 찾는다. */
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/").setCachePeriod(60 * 60 * 24 * 365);
        /* '/css/**'로 호출하는 자원은 '/static/css/' 폴더 아래에서 찾는다. */
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/").setCachePeriod(60 * 60 * 24 * 365);
        /* '/img/**'로 호출하는 자원은 '/static/img/' 폴더 아래에서 찾는다. */
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/image_Game/").setCachePeriod(60 * 60 * 24 * 365);
   }

}