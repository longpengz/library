package com.longpengz.library.modle.entity;

import com.longpengz.dataprocessing.bean.entity.BaseEntity;
import com.longpengz.library.modle.enums.ManagerTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Manager",description = "后台管理员")
public class Manager extends BaseEntity {

    @Column(columnDefinition = "varchar(500) comment '头像'")
    @ApiModelProperty(value = "头像")
    private String avatar;

    @Column(columnDefinition = "varchar(50) comment '名称'")
    @ApiModelProperty(value = "名称")
    private String name;

    @Column(columnDefinition = "varchar(50) comment '账号'")
    @ApiModelProperty(value = "账号")
    private String account;

    @Column(columnDefinition = "varchar(50) comment '密码'")
    @ApiModelProperty(value = "密码")
    private String password;

    @Column(columnDefinition = "varchar(50) comment '密码md5'")
    @ApiModelProperty(value = "密码md5")
    private String passwordMd5;

    @Column(columnDefinition = "varchar(50) comment '类型'")
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "类型")
    private ManagerTypeEnum type;

    @Transient
    @ApiModelProperty(value = "权限列表")
    private List<Permission> permissions;

    @Transient
    @ApiModelProperty(value = "交互标识")
    private String token;

}
