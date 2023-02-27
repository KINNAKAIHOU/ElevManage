package com.scau.zwp.elevmanage.config;

import com.scau.zwp.elevmanage.utils.JWTInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Author: KinnakaIhou
 * @CreateTime: 2023/2/18
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${spring.servlet.multipart.location}")
    public String uploadRootPath;

    /**
     * springboot 无法直接访问静态资源，需要放开资源访问路径。
     * 添加静态资源文件，外部可以直接访问地址
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String fullPath = "file:" + uploadRootPath;
        registry.addResourceHandler("pic/**").addResourceLocations(fullPath);
        System.out.println("静态资源路径：" + fullPath);
    }

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

    //注册拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/user/**", "/column/**", "/order/**", "/product/**")   //其他接口token验证
                .excludePathPatterns("/user/login", "/doc.html#/home");  //所有用户都放心
    }


//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        String fullPath = "file:" + uploadRootPath;
//        registry.addResourceHandler("/**")
//                .addResourceLocations(fullPath);
//        System.out.println("静态资源路径：" + fullPath);
//    }
////
////    @Override
////    public void addCorsMappings(CorsRegistry registry) {
////        registry.addMapping("/**").allowedOrigins("*");
////    }
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        //设置允许跨域的路径
//        registry.addMapping("/**")
//                //设置允许跨域请求的域名
//                //当**Credentials为true时，**Origin不能为星号，需为具体的ip地址【如果接口不带cookie,ip无需设成具体ip】
////                .allowedOrigins("http://localhost:8080", "http://localhost:8090", "http://127.0.0.1:9528")
//                .allowedOrigins("*")
//                //是否允许证书 不再默认开启
////                .allowCredentials(true)
//                //设置允许的方法
//                .allowedMethods("*")
//                //跨域允许时间
//                .maxAge(10000);
//    }

//    //解决跨域问题
//    @Bean
//    public FilterRegistrationBean corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOriginPattern("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//        bean.setOrder(0);
//        return bean;
}

