package com.you.wstro.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration       // 声明该类为配置类
@EnableSwagger2      // 声明开启swagger2
@PropertySource(value ={ "classpath:/config/swagger.properties" }) // 加载文件的路径
public class Swagger2Config
{
    
    /**
     * 通过 createRestApi函数来构建一个DocketBean
     * 函数名,可以随意命名,喜欢什么命名就什么命名
     * @return
     */
    @Bean
    @Profile({"dev","test"})  // 设置只在开发环境和生产环境使用swagger文档，生产环境不允许使用
    @ConditionalOnExpression("${swagger.enable}")  // 是否开启访问接口文档的权限，只有当括号中返回为true时，使用该注解的类才会被实例化，spring容器才会创建bean
    public Docket createRestApi() {
        System.out.println("DocketBean实例化成功----------------------》");
        return new Docket(DocumentationType.SWAGGER_2)
                // 是否启动Swagger，如果false，浏览器中无法访问
                //.enable(false)
                // 设置API分组名称
                .groupName("通用API接口文档")
                // 设置接口文档页面上的一些元素信息
                .apiInfo(apiInfo())
                .select()
                // 扫描的包路径
                .apis(RequestHandlerSelectors.basePackage("com.you.wstro.controller"))
                // 定义哪些路径的接口需要生成api文档，例如：1、any（）：扫描全部  2、none（）：不扫描
                // 支持正则表达式，例如：1、PathSelectors.regex(".*/api/.*") 2、PathSelectors.regex(".*/base/.*")
                .paths(PathSelectors.any())
                .build();
    }
    
    /**
     * 构建 api文档的详细信息函数
     * @return  ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 文档标题
                .title("WSTRO管理系统后台接口文档")
                // 文档描述信息
                .description("基于Swagger2的RESTful风格的API接口文档")
                // 接口地址
                .termsOfServiceUrl("http://localhost:8081/wstro-web")
                // 文档创建者信息
                .contact(new Contact("项目API接口文档","http://localhost:8081/wstro-web","123456789@qq.com"))
                // 版本号
                .version("1.0")
                .build();
    }
}
