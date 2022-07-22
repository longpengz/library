package com.longpengz.library.config;

import com.longpengz.cache.service.CacheService;
import com.longpengz.library.datasource.mapper.ManagerPermissionMapper;
import com.longpengz.library.modle.enums.ManagerTypeEnum;
import com.longpengz.library.modle.response.ManagerLoginRes;
import com.longpengz.restful.bean.APIError;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManageTokenInterceptor implements HandlerInterceptor {


    @Resource
    private CacheService cacheService;

    @Resource
    private ManagerPermissionMapper managerPermissionMapper;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("manageToken");
        if (!StringUtils.hasLength(token)) {
            APIError.NEED_LOGIN();
        }
        ManagerLoginRes managerLoginRes = cacheService.getObject(token);
        if (managerLoginRes == null) {
            APIError.NEED_LOGIN();
        }
        String a = request.getMethod();
        String b = request.getRequestURI();
        if(!ManagerTypeEnum.SUPER.equals(managerLoginRes.getType())
                && managerPermissionMapper.isManagerPermission(managerLoginRes.getId(), request.getRequestURI(),request.getMethod()) == 0){
            APIError.FORBIDDEN();
        }
        return true;
    }
}
