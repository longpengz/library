package com.longpengz.library.modle.response;

import com.longpengz.library.modle.enums.ManagerTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "管理员登录信息")
public class ManagerLoginRes implements Serializable {

    @ApiModelProperty(value = "")
    private Integer id;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "类型")
    private ManagerTypeEnum type;

    @ApiModelProperty(value = "交互标识")
    private String token;
}
