package com.huzheng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author 胡正
 * @Date 2020/3/15 9:31
 * @Description  Api接口文档配置
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
@ComponentScan(basePackages = {"com.huzheng.controller"})
public class SwaggerConfig {

    // 如果要访问的话直接访问 http://localhost:8081/项目名称/swagger-ui.html 就看到文档了
    // 把Bean加到容器中
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * 这是匹配api的信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //大标题
                .title("企业产品在线展示销售平台项目接口文档")
                //描述
                .description("企业产品在线展示销售平台接口测试")
                // 版本号
                .version("1.0.0")
                .termsOfServiceUrl("")
                .license("")
                .licenseUrl("")
                .build();
    }
}
