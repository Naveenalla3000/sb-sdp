//package com.naveen.be.cfg;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:3000") // your frontend URL
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                .allowedHeaders("*")
//                .allowCredentials(true); // if using cookies or credentials
//    }
//}
