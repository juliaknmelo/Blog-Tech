package com.project.alertamogi.configuration;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {
	
	@Bean
	OpenAPI springBlogOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Projeto Alerta Mogi")
						.description("Projeto Alerta Mogi - Projeto Integrador Unisa")
						.version("v0.0.1")
						.license(new License()
								.name("Projeto Integrador")
								.url("https://github.com/juliaknmelo"))
						.contact(new Contact()
								.name("Julia Melo")
								.url("https://github.com/juliaknmelo")
								.email("juliakarennasctoss@outlook.com")))
				.externalDocs(new ExternalDocumentation()
						.description("Github")
						.url("https://github.com/juliaknmelo"));
	}
	
	@Bean
	OpenApiCustomizer costumerGlobalHeaderOpenAPICustomizer() {
		
		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations()
					.forEach(operation -> {
						
						ApiResponses apiResponses = operation.getResponses();
						
						apiResponses.addApiResponse("200", createApiResponse("Sucesso"));
						apiResponses.addApiResponse("201", createApiResponse("Objeto persistido"));
						apiResponses.addApiResponse("204", createApiResponse("Objeto excluído"));
						apiResponses.addApiResponse("400", createApiResponse("Erro na requisição"));
						apiResponses.addApiResponse("401", createApiResponse("Acesso não autorizado"));
						apiResponses.addApiResponse("403", createApiResponse("Acesso proibido"));
						apiResponses.addApiResponse("404", createApiResponse("Objeto não encontrado"));
						apiResponses.addApiResponse("500", createApiResponse("Erro na aplicação"));
						
					}));
		};
	}
	
	private ApiResponse createApiResponse(String message) {
		return new ApiResponse().description(message);
	}

}
