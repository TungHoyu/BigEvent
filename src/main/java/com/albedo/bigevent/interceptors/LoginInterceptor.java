package com.albedo.bigevent.interceptors;

import com.albedo.bigevent.pojo.Result;
import com.albedo.bigevent.utils.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            return true;
        } catch (Exception e) {
            //http响应状态码为401
            response.setStatus(401);
            return false;
        }
    }
}
