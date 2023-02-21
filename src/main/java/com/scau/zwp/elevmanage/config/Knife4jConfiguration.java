package com.scau.zwp.elevmanage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @Description:
 * @Author: KinnakaIhou
 * @CreateTime: 2023/2/18
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("电梯安检维修管理平台 API")
                        .description("用于《电梯安检维修管理平台》项目的Api自动生成的Swagger2文档，可以导入ApiFox")
                        .termsOfServiceUrl("http://localhost:8080/")
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("1.X版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.scau.zwp.elevmanage.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
