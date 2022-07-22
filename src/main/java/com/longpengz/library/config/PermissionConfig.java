package com.longpengz.library.config;

import com.longpengz.library.annotation.AnonUrl;
import com.longpengz.library.modle.entity.Permission;
import com.longpengz.library.service.ManagerPermissionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;

@Configuration
public class PermissionConfig implements ApplicationListener<ApplicationReadyEvent> {


    @Resource
    private RequestMappingHandlerMapping mapping;

    @Resource
    private ManagerPermissionService managerPermissionService;



    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        mapping.getHandlerMethods().forEach((requestMappingInfo, handlerMethod) -> filterUrl(requestMappingInfo.getPatternsCondition(), requestMappingInfo.getMethodsCondition(),handlerMethod));
    }

    private void filterUrl(PatternsRequestCondition patternsCondition,
                            RequestMethodsRequestCondition methodsCondition,
                            HandlerMethod handlerMethod){
        patternsCondition.getPatterns().forEach(url -> {
            if(url.contains("manage")){
                methodsCondition.getMethods().forEach(method -> processUrl(method.name() , url , handlerMethod));
            }
        });
    }

    private void processUrl(String method, String url, HandlerMethod handlerMethod){
        AnonUrl anonUrl = handlerMethod.getMethod().getAnnotation(AnonUrl.class);
        if(!ObjectUtils.isEmpty(anonUrl)){
            return;
        }
        ApiOperation apiOperation = handlerMethod.getMethod().getAnnotation(ApiOperation.class);
        managerPermissionService.insertPermission(Permission.builder()
                .method(method)
                .name(ObjectUtils.isEmpty(apiOperation) ? "":apiOperation.value())
                .path(url).build());
    }


}
