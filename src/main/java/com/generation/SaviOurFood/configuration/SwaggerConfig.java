package com.generation.SaviOurFood.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    OpenAPI springSaviOurFoodOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Project Integrator Savi Our Food")
                        .description("Project Savi Our Food - Generation Brasil")
                        .version("v0.0.1")
                        .license(new License()
                                .name("Generation Brasil")
                                .url("https://brazil.generation.org/"))
                        .contact(new Contact()
                                .url("Generation Brasil")
                                .url("https://github.com/Project-SaviOurFood/Back-end")
                                .email("https://github.com/Project-SaviOurFood/Back-end")))
                .externalDocs(new ExternalDocumentation()
                        .description("Github")
                        .url("https://github.com/Project-SaviOurFood/Back-end"));
    }

    private ApiResponse createApiResponse(String message) {
        return new ApiResponse().description(message);
    }

    @Bean
    OpenApiCustomizer customerGlobalHeaderOpenApiCustomiser() {

        return openApi -> {
            openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
                ApiResponses apiResponses = operation.getResponses();

                apiResponses.addApiResponse("200", createApiResponse("Success!"));
                apiResponses.addApiResponse("201", createApiResponse("Persisted Object!"));
                apiResponses.addApiResponse("204", createApiResponse("Deleted Object!"));
                apiResponses.addApiResponse("400", createApiResponse("Error in Request!"));
                apiResponses.addApiResponse("401", createApiResponse("Unauthorized access!"));
                apiResponses.addApiResponse("403", createApiResponse("Access Prohibited!"));
                apiResponses.addApiResponse("404", createApiResponse("Object not found!"));
                apiResponses.addApiResponse("500", createApiResponse("Application Error!"));
            }));
        };
    }
}
