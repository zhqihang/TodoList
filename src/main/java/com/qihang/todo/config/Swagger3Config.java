package com.qihang.todo.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author: zhqihang
 * @Date: 2024/06/17
 * @Project: TodoList
 * @Description: Swagger配置类
 *
 */
@Configuration
public class Swagger3Config {
    @Bean
    public Docket createRestApi(Environment environment) {
        // 设置显示swagger的环境
        Profiles profile = Profiles.of("dev", "prod");
        // 获取当前的运行环境：多个string参数的方法提示过时,参数接收core包下的profile
        boolean flag = environment.acceptsProfiles(profile);

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .groupName("接口文档")
                // 根据环境切换
                .enable(flag)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.ant("/category/**"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger3接口文档")
                .description("更多请咨询服务开发者-启航。")
                .contact(new Contact("启航", "http://github.com/zhqihang", "qihang9903@163.com"))
                .version("1.0")
                .build();
    }
}
