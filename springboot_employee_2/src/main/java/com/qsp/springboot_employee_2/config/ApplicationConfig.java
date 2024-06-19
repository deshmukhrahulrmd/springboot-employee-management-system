package com.qsp.springboot_employee_2.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Configuration
@EnableSwagger2
public class ApplicationConfig {
	
//	url to check Swagger document which we are created :- http://localhost:8080/swagger-ui.html#  if we use springboot version-2 but we used springboot version-3 so it is not working
	
	@Bean	// To retrieve data from third party we use Bean
	public Docket getDocket() {	     //Name,   url,         emailId
		Contact contact = new Contact("Rahul", "rahul.com", "rahul@gmail.com");
		List<VendorExtension> vendorExtensions = new ArrayList<VendorExtension>();
						//1. Title of project.
						//2. For what purpose we are building this app
						//3. Current Version of application
						//4. Our Web site url
						//5. To conatct us we pass contact object we created
						//6. Lisenc Id
						//7. Lisen url
						//8. Extensions Object (VenderExtenction)
		ApiInfo apiInfo = new ApiInfo("EMS", "To manage employees", "Version 1.0", "www.ems.com", contact, "RAHUL001", "www.rahul.com", vendorExtensions);
		
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.qsp.springboot_employee_2")).build().apiInfo(apiInfo).useDefaultResponseMessages(false);
	}
}
