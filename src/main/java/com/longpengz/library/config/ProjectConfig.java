package com.longpengz.library.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "project")
public class ProjectConfig {

    // 项目名称 拼音或者英文
    private String name;

    // 上传文件路径
    private String uploadPath;

    // 静态文件路径
    private String staticPath;

    // 项目后台域名
    private String url;

    // 前端Web更新包存放路径
    private String frontUpdatePath;

    // 前端APP页面相关文件路径
    private String appWebPath;

    // 前端管理端页面相关文件路径
    private String managerWebPath;

    // 相关权限校验账号
    private String username;

    // 相关权限校验密码
    private String password;


}
