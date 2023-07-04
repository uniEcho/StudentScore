package com.uniecho.score.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author by uni_E
 * @classname SpringDocConfig
 * @description TODO
 */
@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI restfulOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("学生成绩管理系统")
                        .description("学生成绩管理系统 API")
                        .version("v0.0.1")
                        .license(new License().name("Springdoc-OpenAPI v2.1.0").url("https://springdoc.org/")));

    }
}
