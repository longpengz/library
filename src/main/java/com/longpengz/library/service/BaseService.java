package com.longpengz.library.service;

import com.longpengz.cache.service.CacheService;
import com.longpengz.library.datasource.repository.ManagerRepository;
import com.longpengz.library.modle.entity.Manager;
import com.longpengz.library.modle.response.ManagerLoginRes;
import com.longpengz.restful.bean.APIError;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class BaseService {

    @Resource
    protected CacheService cacheService;

    @Resource
    protected ManagerRepository managerRepository;

    /**
     * 获取请求内容
     * @author longpengZ
     */
    protected HttpServletRequest getCurrentRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    /**
     * 根据key获取请求头
     * @author longpengZ
     */
    public Object getHeader(String key) {
        return getCurrentRequest().getHeader(key);
    }

    /**
     * 根据key获取请求头字符串
     * @author longpengZ
     */
    public String getStringHeader(String key) {
        return (String) getHeader(key);
    }

    /**
     * @author longpengZ
     */
    public Manager getManagerLoginInfo(){
        String manageToken = getStringHeader("manageToken");
        if(!StringUtils.hasLength(manageToken)){
            APIError.NEED_LOGIN();
        }
        ManagerLoginRes managerLoginRes = null;
        try {
            managerLoginRes = cacheService.getObject(manageToken);
        }catch (Exception e){
            e.printStackTrace();
            cacheService.deleteObject(manageToken);
        }
        if(ObjectUtils.isEmpty(managerLoginRes)){
            APIError.NEED_LOGIN();
        }
        Manager manager = managerRepository.findById(managerLoginRes.getId()).orElse(null);
        if(ObjectUtils.isEmpty(manager)
                || manager.getPresenceStatus().equals(0)){
            APIError.NEED_LOGIN();
        }
        manager.setToken(manageToken);
        return manager;
    }



}
