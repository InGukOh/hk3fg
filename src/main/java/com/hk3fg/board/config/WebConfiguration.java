package com.hk3fg.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {
    private static final String[] ClassPath_Resource_Location = {"classpath:/"};
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**").addResourceLocations(ClassPath_Resource_Location ).setCachePeriod(60 * 60 * 24 * 365);
        registry.addResourceHandler("/common/**").addResourceLocations("classpath:/templates/common/").setCachePeriod(60 * 60 * 24 * 365);
        /* '/js/**'로 호출하는 자원은 '/static/js/' 폴더 아래에서 찾는다. */
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/").setCachePeriod(60 * 60 * 24 * 365);
        /* '/static/bootstrap-5.1.3-dist/**'로 호출하는 자원은 '/static/bootstrap-5.1.3-dist/' 폴더 아래에서 찾는다. */
        registry.addResourceHandler("/bootstrap-5.1.3-dist/**").addResourceLocations("classpath:/static/bootstrap-5.1.3-dist/").setCachePeriod(60 * 60 * 24 * 365);
        /* '/static/css/**'로 호출하는 자원은 '/static/css/' 폴더 아래에서 찾는다. */
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/").setCachePeriod(60 * 60 * 24 * 365);
        /* '/img/**'로 호출하는 자원은 '/static/img/' 폴더 아래에서 찾는다. */
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/").setCachePeriod(60 * 60 * 24 * 365);
        /* '/font/**'로 호출하는 자원은 '/static/font/' 폴더 아래에서 찾는다. */
        registry.addResourceHandler("/font/**").addResourceLocations("classpath:/static/font/").setCachePeriod(60 * 60 * 24 * 365);

        //registry.addResourceHandler("/board/**").addResourceLocations("classpath:/templates/board/").setCachePeriod(60 * 60 * 24 * 365);
        registry.addResourceHandler("/Bungtermelon_Marge/**").addResourceLocations("classpath:/Game_BungterMelon_Marge/").setCachePeriod(60 * 60 * 24 * 365);
        registry.addResourceHandler("cocos2d-js-min.js").addResourceLocations("classpath:/Game_BungterMelon_Marge/").setCachePeriod(60 * 60 * 24 * 365);
        registry.addResourceHandler("/src/project.js").addResourceLocations("classpath:/Game_BungterMelon_Marge/").setCachePeriod(60 * 60 * 24 * 365);
        registry.addResourceHandler("/res/import/").addResourceLocations("classpath:/Game_BungterMelon_Marge/res/import/").setCachePeriod(60 * 60 * 24 * 365);

   }

}