package com.wen.config;

/**
 * Created by wenfeng on 2017/11/27.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars*")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     *
     * @return
     */
    @Bean
    public Docket accessToken() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("api")// 定义组
                .select() // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("com.wen.controller")) // 拦截的包路径
                .paths(regex("/api/.*"))// 拦截的接口路径
                .build() // 创建
                .apiInfo(apiInfo()); // 配置说明
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()//
                .title("Swagger demo")// 标题
                .description("Smarty chair")// 描述
                .termsOfServiceUrl("http://www.roncoo.com")//
                .contact(new Contact("wujing", "http://www.roncoo.com", "297115770@qq.com"))// 联系
                //.license("Apache License Version 2.0")// 开源协议
                //.licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")// 地址
                .version("1.0")// 版本
                .build();
    }
}