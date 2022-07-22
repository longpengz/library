package com.longpengz.library.datasource.mapper;

import com.longpengz.library.datasource.mapper.filter.ManagerPermissionFilter;
import com.longpengz.library.modle.entity.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Mapper
public interface ManagerPermissionMapper {

    @Delete("delete from manager_permission where manager_id = #{managerId}")
    void deleteByManagerId(Integer managerId);

    @Select(" select IFNULL(count(mp.id), 0) " +
            " from manager_permission mp left join permission p on mp.permission_id = p.id" +
            " where mp.manager_id = #{managerId} and p.path = #{path} and p.method = #{method}")
    Integer isManagerPermission(Integer managerId, String path, String method);

    @SelectProvider(type = ManagerPermissionProvider.class, method = "getManagerPermission")
    List<Permission> getPermissions(ManagerPermissionFilter managerPermissionFilter);

    class ManagerPermissionProvider{
        public String getManagerPermission(ManagerPermissionFilter managerPermissionFilter){
            return new SQL(){{
                SELECT("p.*");
                FROM("manager_permission mp,manager m,permission p");
                WHERE("mp.presence_status = 1 and m.presence_status = 1 and p.presence_status = 1");
                WHERE("m.id = mp.manager_id and p.id = mp.permission_id");
                WHERE("(p.level = 1 or p.name = '首页')");
                if(!ObjectUtils.isEmpty(managerPermissionFilter.getManagerId())){
                    WHERE("m.id = #{managerId}");
                }
            }}.toString();
        }
    }
}
