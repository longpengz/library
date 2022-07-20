package com.longpengz.library.modle.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "初始化超级管理员请求参数")
public class InitManagerReq {

    @NotNull(message = "名称不能为空")
    @ApiModelProperty(value = "名称")
    private String name;

    @NotNull(message = "账号不能为空")
    @ApiModelProperty(value = "账号")
    private String account;

    @NotNull(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;
}
