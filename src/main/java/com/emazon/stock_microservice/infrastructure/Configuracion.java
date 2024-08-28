package com.emazon.stock_microservice.infrastructure;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Stock Microservice API")
                        .version("1.0")
                        .description("Documentación de la API para el servicio de gestión de marcas y categorías"));
    }

    @Bean
    public GroupedOpenApi marcaApi() {
        return GroupedOpenApi.builder()
                .group("marcas")
                .pathsToMatch("/api/marcas/**")
                .build();
    }

    @Bean
    public GroupedOpenApi categoriaApi() {
        return GroupedOpenApi.builder()
                .group("categorias")
                .pathsToMatch("/api/categorias/**")
                .build();
    }
    @Bean
    public GroupedOpenApi articuloaApi() {
        return GroupedOpenApi.builder()
                .group("articulos")
                .pathsToMatch("/api/articulos/**")
                .build();
    }
}
