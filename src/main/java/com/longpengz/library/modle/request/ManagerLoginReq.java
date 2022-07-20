package com.longpengz.library.modle.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "后台管理员登陆请求参数")
public class ManagerLoginReq {

    @NotBlank(message = "账号不能为空")
    @ApiModelProperty(value = "账号")
    private String account;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码 md5")
    private String password;
}
