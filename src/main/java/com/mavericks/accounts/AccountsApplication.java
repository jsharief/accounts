package com.mavericks.accounts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
	info = @io.swagger.v3.oas.annotations.info.Info(
		title = "Accounts Application",
		version = "1.0",
		description = "Accounts Application",
		contact = @io.swagger.v3.oas.annotations.info.Contact(
			name = "Mavericks",
			email = "admin@themavericks.pro"
		)
	),
		externalDocs = @io.swagger.v3.oas.annotations.ExternalDocumentation(
			description = "Find out more",
			url = "http://localhost:8080/mavericks/swagger-ui.html"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
