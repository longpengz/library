package com.longpengz.library.datasource.mapper.filter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "管理员权限视图筛选参数")
public class ManagerPermissionFilter {

    @ApiModelProperty(value = "管理员id")
    private Integer managerId;
}
