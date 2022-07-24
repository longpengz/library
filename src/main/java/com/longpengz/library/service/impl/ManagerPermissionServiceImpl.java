package com.longpengz.library.service.impl;

import com.github.wenhao.jpa.PredicateBuilder;
import com.github.wenhao.jpa.Specifications;
import com.longpengz.dataprocessing.bean.pojo.SeachForm;
import com.longpengz.dataprocessing.bean.pojo.SpecificationUtil;
import com.longpengz.library.datasource.mapper.ManagerPermissionMapper;
import com.longpengz.library.datasource.repository.ManagerPermissionRepository;
import com.longpengz.library.datasource.repository.ManagerRepository;
import com.longpengz.library.datasource.repository.PermissionRepository;
import com.longpengz.library.modle.entity.Manager;
import com.longpengz.library.modle.entity.ManagerPermission;
import com.longpengz.library.modle.entity.Permission;
import com.longpengz.library.modle.enums.ManagerTypeEnum;
import com.longpengz.library.modle.request.InitManagerReq;
import com.longpengz.library.modle.request.ManagerLoginReq;
import com.longpengz.library.modle.response.ManagerLoginRes;
import com.longpengz.library.service.BaseService;
import com.longpengz.library.service.ManagerPermissionService;
import com.longpengz.restful.bean.APIError;
import com.longpengz.utils.Captcha;
import com.longpengz.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author longpengZ
 */
@Service
@RequiredArgsConstructor
public class ManagerPermissionServiceImpl extends BaseService implements ManagerPermissionService {
    private final ManagerRepository managerRepository;
    private final PermissionRepository permissionRepository;
    private final ManagerPermissionRepository managerPermissionRepository;
    private final ManagerPermissionMapper managerPermissionMapper;


    @Override
    public void initSuperManager(InitManagerReq initManagerReq){
        if(managerRepository.count(Specifications.<Manager>and()
                .eq("presenceStatus",1)
                .eq("type", ManagerTypeEnum.SUPER).build()) > 0){
            APIError.e("超级管理员已存在");
        }
        insertManager(Manager.builder()
                .name(initManagerReq.getName())
                .account(initManagerReq.getAccount())
                .password(initManagerReq.getPassword())
                .type(ManagerTypeEnum.SUPER).build());

    }

    @Override
    @Transactional
    public Manager insertManager(Manager manager){
        if(managerRepository.count(Specifications.<Manager>and()
                .eq("presenceStatus",1)
                .eq("account", manager.getAccount())
                .ne(!ObjectUtils.isEmpty(manager.getId()),"id",manager.getId()).build()) > 0){
            APIError.e("账号已经存在");
        }
        if(!ObjectUtils.isEmpty(manager.getId()) && !manager.getId().equals(0)){
            Manager managerOld = managerRepository.findById(manager.getId()).orElse(null);
            if(ObjectUtils.isEmpty(managerOld)){
                APIError.NOT_FOUND();
            }
            manager.setPresenceStatus(1);
            if(ObjectUtils.isEmpty(manager.getPassword())){
                manager.setPassword(managerOld.getPassword());
            }
        }
        manager.setPasswordMd5(DigestUtils.md5DigestAsHex(manager.getPassword().getBytes(StandardCharsets.UTF_8)));
        managerRepository.save(manager);
        insertManagerPermission(manager,manager.getPermissions());
        return manager;
    }


    @Override
    @Transactional
    public void insertManagerPermission(Manager manager,List<Permission> permissions){
        managerPermissionMapper.deleteByManagerId(manager.getId());
        if(CollectionUtils.isEmpty(permissions)){
            return;
        }
        permissions.forEach(it -> managerPermissionRepository.save(ManagerPermission.builder()
                .managerId(manager.getId())
                .permissionId(it.getId()).build()));
    }


    @Override
    public ManagerLoginRes managerLogin(ManagerLoginReq managerLoginReq){
        Manager manager = managerRepository.findOne(Specifications.<Manager>and()
                .eq("presenceStatus", 1)
                .eq("account", managerLoginReq.getAccount())
                .eq("passwordMd5", managerLoginReq.getPassword()).build()).orElse(null);
        if(ObjectUtils.isEmpty(manager)){
            APIError.e("账号或密码错误");
        }
        ManagerLoginRes managerLoginRes = getManagerLoginRes(manager);
        cacheService.saveObject(managerLoginRes.getToken(), managerLoginRes);
        return managerLoginRes;
    }


    /**
     * 构建后台管理员登陆信息
     * @author longpengZ
     */
    private ManagerLoginRes getManagerLoginRes(Manager manager){
        String token = manager.getToken();
        if(!StringUtils.hasLength(token)){
            token = Captcha.getUUIDNumber(32);
        }
        ManagerLoginRes managerLoginRes = new ManagerLoginRes();
        BeanUtils.copyProperties(manager, managerLoginRes);
        managerLoginRes.setToken(token);
        return managerLoginRes;
    }


    @Override
    public ManagerLoginRes getLoginInfo(){
        return getManagerLoginRes(getManagerLoginInfo());
    }

    @Override
    public Page<Manager> getManagers(SeachForm seachForm){
        PredicateBuilder<Manager> spec = SpecificationUtil.filter(seachForm);
        spec.eq("type",ManagerTypeEnum.MANAGER);
        spec.eq("presenceStatus", 1);
        return managerRepository.findAll(spec.build()
                , seachForm.pageRequest());
    }

    @Override
    public Manager getManager(Integer managerId){
        Manager manager = managerRepository.findById(managerId).orElse(null);
        if(ObjectUtils.isEmpty(manager)){
            APIError.NOT_FOUND();
        }
        List<Integer> permissionIds = managerPermissionRepository.findAll(Specifications.<ManagerPermission>and()
                        .eq("presenceStatus", 1)
                        .eq("managerId", manager.getId()).build())
                .stream().map(ManagerPermission::getPermissionId).toList();
        manager.setPermissions(permissionRepository.findAll(Specifications.<Permission>and()
                .eq("presenceStatus",1)
                .in("id", permissionIds.toArray()).build()));
        return manager;
    }

    @Override
    public List<Permission> getPermissions(){
        return permissionRepository.findAll(Specifications.<Permission>and()
                .eq("presenceStatus",1).build());
    }

    @Override
    @Transactional
    public void deleteManager(String ids){
        managerRepository.softDelete(StringUtil.toIntArray(ids));
    }

    @Override
    public void insertPermission(Permission permission) {
        permissionRepository.findOne(Specifications.<Permission>and()
                .eq("presenceStatus",1)
                .eq("method", permission.getMethod())
                .eq("path", permission.getPath()).build())
                .ifPresent(it -> permission.setId(it.getId()));
        permission.setPresenceStatus(1);
        permissionRepository.save(permission);
    }

}
