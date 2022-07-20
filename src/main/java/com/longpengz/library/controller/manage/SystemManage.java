package com.longpengz.library.controller.manage;

import com.longpengz.dataprocessing.bean.pojo.PageResult;
import com.longpengz.dataprocessing.bean.pojo.SeachForm;
import com.longpengz.library.modle.constants.RestPrefixConstant;
import com.longpengz.library.modle.entity.Manager;
import com.longpengz.library.modle.entity.Permission;
import com.longpengz.library.modle.enums.ManagerTypeEnum;
import com.longpengz.library.service.ManagerPermissionService;
import com.longpengz.restful.bean.API;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"管理后台-系统管理"})
@RestController
@RequestMapping(RestPrefixConstant.MANAGE_SYSTEM_PREFIX)
@RequiredArgsConstructor
public class SystemManage {

    private final ManagerPermissionService managerPermissionService;

    @GetMapping("managers")
    @ApiOperation("获取管理员列表")
    public API<PageResult<Manager>> getManagers(SeachForm seachForm){
        return API.ok(PageResult.jpaOf(managerPermissionService.getManagers(seachForm)));
    }

    @GetMapping("manager")
    @ApiOperation("获取管理员详情")
    public API<Manager> getManager(@RequestParam Integer managerId){
        return API.ok(managerPermissionService.getManager(managerId));
    }

    @PostMapping("manager")
    @ApiOperation("添加编辑管理员")
    public API<Manager> insertManager(@RequestBody Manager manager){
        manager.setType(ManagerTypeEnum.MANAGER);
        return API.ok(managerPermissionService.insertManager(manager));
    }

    @DeleteMapping("manager")
    @ApiOperation("删除管理员")
    public API<String> deleteManager(@RequestParam String ids){
        managerPermissionService.deleteManager(ids);
        return API.ok("成功");
    }

    @GetMapping("permissions")
    @ApiOperation("获取所有权限")
    public API<List<Permission>> getPermissions(){
        return API.ok(managerPermissionService.getPermissions());
    }

}
