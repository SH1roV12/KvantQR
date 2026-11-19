package ru.yaharuott.servhack.adapter.in.web.staff;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.yaharuott.servhack.domain.exceptions.InvalidStaffCodeException;

@Component
public class StaffOnlyInterceptor implements HandlerInterceptor {
    @Value("${app.staff.secret-key}")
    private String staffSecretKey;


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        if (handler instanceof HandlerMethod handlerMethod){
            if (!handlerMethod.hasMethodAnnotation(StaffOnly.class)){
                return true;
            }

            String headerCode = request.getHeader("X-Staff-Code");
            if (headerCode == null || !headerCode.trim().equals(staffSecretKey)) {
                throw new InvalidStaffCodeException("Access denied");
            }

        }
        return true;
    }
}
