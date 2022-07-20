package com.longpengz.library.service;

import com.longpengz.dataprocessing.bean.pojo.SeachForm;
import com.longpengz.library.modle.entity.Manager;
import com.longpengz.library.modle.entity.Permission;
import com.longpengz.library.modle.request.InitManagerReq;
import com.longpengz.library.modle.request.ManagerLoginReq;
import com.longpengz.library.modle.response.ManagerLoginRes;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 管理员及管理员权限
 * @author longpengZ
 */
public interface ManagerPermissionService {

    /**
     * 初始化超级管理员
     * @author longpengZ
     */
    void initSuperManager(InitManagerReq initManagerReq);

    /**
     * 添加编辑管理员
     * @author longpengZ
     */
    Manager insertManager(Manager manager);

    /**
     * 添加编辑管理员权限
     * @author longpengZ
     */
    void insertManagerPermission(Manager manager,List<Permission> permissions);

    /**
     * 后台管理员登陆
     * @author longpengZ
     */
    ManagerLoginRes managerLogin(ManagerLoginReq managerLoginReq);

    /**
     * 获取后台管理员当前登陆信息
     * @author longpengZ
     */
    ManagerLoginRes getLoginInfo();

    /**
     * 获取管理员列表
     * @author longpengZ
     */
    Page<Manager> getManagers(SeachForm seachForm);

    /**
     * 获取管理员详情
     * @author longpengZ
     * @param managerId 管理员id
     */
    Manager getManager(Integer managerId);

    /**
     * 获取所有菜单权限
     * @author longpengZ
     */
    List<Permission> getPermissions();

    /**
     * 删除管理员
     * @author longpengZ
     * @param ids 删除id
     */
    void deleteManager(String ids);



}
