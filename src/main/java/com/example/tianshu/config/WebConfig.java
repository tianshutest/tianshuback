package com.example.tianshu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/tianshu/demands/**")
                .allowedOrigins("http://127.0.0.1:5500", "*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*"); // 允许的请求头;
        registry.addMapping("/tianshu/items/**") // 根据您的实际路径调整
                .allowedOrigins("http://wl.tsjiaoyi.asia:10", "http://127.0.0.1:5500","http://localhost:7531","http://127.0.0.1:7531")
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的请求方法
                .allowedHeaders("*"); // 允许的请求头
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:7531")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8081")  // 允许来自 localhost:8080 的请求
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // 允许的请求方式
                .allowedHeaders("*")  // 允许的请求头
                .allowCredentials(true)  // 是否允许发送凭证
                .maxAge(3600);  // 预检请求的有效时间（秒）
    }
}
