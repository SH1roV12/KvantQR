
package ru.yaharuott.servhack.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import ru.yaharuott.servhack.adapter.in.web.staff.StaffOnly;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Кванториум: Цифровой пропуск API")
                        .version("1.0"))
                .components(new Components()
                        .addSecuritySchemes("StaffCodeAuth", new SecurityScheme()
                                .name("X-Staff-Code")
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .description("Введите ключ сотрудника (например, KV-ADMIN-2026)")));
    }

    @Bean
    public OperationCustomizer customizeStaffOperations() {
        return (operation, handlerMethod) -> {
            if (handlerMethod.hasMethodAnnotation(StaffOnly.class)) {
                operation.addSecurityItem(new SecurityRequirement().addList("StaffCodeAuth"));
            }
            return operation;
        };
    }
}
