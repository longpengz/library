package com.longpengz.library.controller.manage;

import com.longpengz.library.modle.constants.RestPrefixConstant;
import com.longpengz.library.modle.request.InitManagerReq;
import com.longpengz.library.modle.request.ManagerLoginReq;
import com.longpengz.library.modle.response.ManagerLoginRes;
import com.longpengz.library.service.ManagerPermissionService;
import com.longpengz.restful.bean.API;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"管理后台-登陆相关"})
@RestController
@RequestMapping(RestPrefixConstant.MANAGE_REST_PREFIX)
@RequiredArgsConstructor
public class ManagerLoginManage {

    private final ManagerPermissionService managerPermissionService;

    @PostMapping("login")
    @ApiOperation("登陆")
    public API<ManagerLoginRes> login(@RequestBody @Valid ManagerLoginReq managerLoginReq){
        return API.ok(managerPermissionService.managerLogin(managerLoginReq));
    }

    @GetMapping("loginInfo")
    @ApiOperation("获取登陆信息")
    public API<ManagerLoginRes> getInfo(){
        return API.ok(managerPermissionService.getLoginInfo());
    }

    @PostMapping("initManager")
    @ApiOperation("初始化超级管理员")
    public API<String> initSuperManager(@RequestBody @Valid InitManagerReq initManagerReq){
        managerPermissionService.initSuperManager(initManagerReq);
        return API.ok("成功");
    }

}
