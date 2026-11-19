package ru.yaharuott.servhack.adapter.in.web;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.yaharuott.servhack.adapter.in.web.staff.StaffOnlyInterceptor;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private final StaffOnlyInterceptor staffOnlyInterceptor;

    public WebConfiguration(StaffOnlyInterceptor staffOnlyInterceptor) {
        this.staffOnlyInterceptor = staffOnlyInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(staffOnlyInterceptor);
    }
}
