package com.volvadvit.talkie.util;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            String queryArgs = request.getQueryString() != null ? request.getQueryString() : "";
            String url = request.getRequestURI().toString();
            if (!queryArgs.isEmpty()) {
                url += "?" + queryArgs;
            }
            response.setHeader("Turbolinks-Location", url);
        }
    }
}
