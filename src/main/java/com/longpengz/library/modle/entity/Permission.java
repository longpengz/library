package com.longpengz.library.modle.entity;

import com.longpengz.dataprocessing.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Permission",description = "权限")
public class Permission extends BaseEntity {

    @Column(columnDefinition = "varchar(50) comment '名称'")
    @ApiModelProperty(value = "名称")
    private String name;

    @Column(columnDefinition = "varchar(50) comment '请求方式'")
    @ApiModelProperty(value = "请求方式")
    private String method;

    @Column(columnDefinition = "varchar(255) comment '请求路径'")
    @ApiModelProperty(value = "请求路径")
    private String path;
}
