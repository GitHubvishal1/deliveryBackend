/**
 * 
 */
package com.cg.aps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Vishal Rana
 *
 */
@SpringBootApplication // Used on a configuration class and triggers auto-configuration and component
						// scanning. Equivalent of @Configuration, @EnableAutoConfiguration, @ComponentScan.
@EnableJpaAuditing // Maintaining history records
@EnableSwagger2 // Enables Swagger support in the class
public class ApartmentSecurityManagementApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApartmentSecurityManagementApplication.class, args);
	}

	/**
	 * @return
	 */
	@Bean // Docket bean can be configured for more control over the API documentation.
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.cg.aps.controller")).build();
	}

	/**
	 * @return
	 */
	private ApiInfo apiInfo() { // Custom information about the API
		return new ApiInfoBuilder().title("Apartment Security Management").description("API reference for developers")
				.version("V1.0").build();
	}
}
