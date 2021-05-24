package com.ssafy.happyhouse.config;
import java.util.HashSet;
import java.util.Set;

// git test minsang 05/18 20:22
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.*;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//http://localhost:9999/ssabang/swagger-ui.html

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("ssabang")
				.description("싸방에서 집찾아방 ")
				.version("1.0")
				.build();
	}
	
	private Set<String> getConsumeContentTypes(){
		Set<String> consumes = new HashSet<>();
		consumes.add("application/json;charset=UTF-8");
		consumes.add("application/x-www-form-urlencoded");
		return consumes;
	}
	
	@Bean
	public Docket commonApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.consumes(getConsumeContentTypes())
				.produces(getConsumeContentTypes())
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ssafy.happyhouse.controller"))
				.paths(PathSelectors.any())
				.build();
	}

//	@Bean
//	public Docket postsApi() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.groupName("happyhouse")
//				.apiInfo(apiInfo())
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.ssafy.happyhouse.controller"))
////				.paths(PathSelectors.ant("/book/**"))
//				.build();
//	}
//
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder().title("HAPPYHOUSE API")
//				.description("<h2>SSAFY VUE Book WorkShop API Reference for Developers</h2><img src=\"ssafy.png\">")
//				.termsOfServiceUrl("https://edu.ssafy.com")
//				.license("SSAFY License")
//				.licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp").version("1.0").build();
//	}

}
