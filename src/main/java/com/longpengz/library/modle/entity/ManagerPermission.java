package com.longpengz.library.modle.entity;
import com.longpengz.dataprocessing.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ManagerPermission",description = "管理员权限中间表")
public class ManagerPermission extends BaseEntity {

    @ApiModelProperty(value = "管理员id")
    private Integer managerId;

    @ApiModelProperty(value = "权限id")
    private Integer permissionId;
}
