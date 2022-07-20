package com.longpengz.library.config;

import com.longpengz.cache.service.CacheService;
import com.longpengz.restful.bean.APIError;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManageTokenInterceptor implements HandlerInterceptor {


    @Resource
    private CacheService cacheService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("manageToken");
        if (!StringUtils.hasLength(token)) {
            APIError.NEED_LOGIN();
        }
        Object o = cacheService.getObject(token);
        if (o == null) {
            APIError.NEED_LOGIN();
        }
        return true;
    }
}
