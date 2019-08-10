package com.example.demo.interceptor;

import com.example.demo.auth.Auth;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    private static int i = 0;
    @Override
    public boolean  preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //handle一般为HandlerMethod类型
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)){
            System.out.println("cat cast handler to HandlerMethod.class");
            return true;
        }
        //获取注解内传来的值进行判断
        Auth auth =((HandlerMethod)handler).getMethod().getAnnotation(Auth.class);
        if (auth == null){
            System.out.println("cant find @Auth in this uri"+request.getRequestURI());
            return true;
        }
        String admin = auth.user();
        if (!admin.equals(request.getParameter("user"))){
            System.out.println("permission denied");
            response.setStatus(403);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
