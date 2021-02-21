package com.blog.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig  implements WebMvcConfigurer {

    /*
    * Copied straight from the the spring docs
    */
    // LEARN IT


    public void addCorsMappings(CorsRegistry corsRegistry){
        corsRegistry.addMapping("/api/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("*")

                .maxAge(3600L)
                .allowedHeaders("*")
                .exposedHeaders("Authorization")
                .allowCredentials(true);


    }

}
