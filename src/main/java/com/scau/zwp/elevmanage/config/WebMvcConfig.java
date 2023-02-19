package com.scau.zwp.elevmanage.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Author: KinnakaIhou
 * @CreateTime: 2023/2/18
 */
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${spring.servlet.multipart.location}")
    public String uploadRootPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String fullPath = "file:" + uploadRootPath;
        registry.addResourceHandler("/pic/**")
                .addResourceLocations(fullPath);
        System.out.println("静态资源路径：" + fullPath);
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowedOrigins("*");
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                //当**Credentials为true时，**Origin不能为星号，需为具体的ip地址【如果接口不带cookie,ip无需设成具体ip】
//                .allowedOrigins("http://localhost:8080", "http://localhost:8090", "http://127.0.0.1:9528")
                .allowedOrigins("*")
                //是否允许证书 不再默认开启
//                .allowCredentials(true)
                //设置允许的方法
                .allowedMethods("*")
                //跨域允许时间
                .maxAge(10000);
    }
}