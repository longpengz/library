package com.longpengz.library.modle.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "管理员类型")
public enum ManagerTypeEnum {
    @ApiModelProperty("超级管理员")
    SUPER,
    @ApiModelProperty("普通管理员")
    MANAGER
}
