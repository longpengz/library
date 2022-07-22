package com.longpengz.library.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.io.File;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private ProjectConfig projectConfig;


    @Bean
    public CommonInterceptor commonInterceptor() {
        return new CommonInterceptor();
    }


    @Bean
    public ManageTokenInterceptor managerTokenInterceptor() {
        return new ManageTokenInterceptor();
    }


    /**
     * 拦截请求
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonInterceptor()).addPathPatterns("/**").excludePathPatterns("/doc.html","/project/**");

        // manage接口鉴权
        registry.addInterceptor(managerTokenInterceptor())
                .addPathPatterns("/manage/**")
                .excludePathPatterns("/manage/login","/manage/initManager");


    }

    /**
     * 静态资源配置
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }



    /**
     * 首页配置
     */
    @Bean
    public ITemplateResolver templateResolver(){
        return getResolver(projectConfig.getStaticPath()+File.separator+projectConfig.getName()+ File.separator);
    }

    private FileTemplateResolver getResolver(String path){
        FileTemplateResolver resolver =new FileTemplateResolver();
        resolver.setPrefix(path);
        resolver.setSuffix(".html");
        resolver.setOrder(1);
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCacheable(false);
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

}
