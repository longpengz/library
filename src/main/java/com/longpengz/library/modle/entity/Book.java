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
@ApiModel(value = "Book",description = "书本实体")
public class Book extends BaseEntity {

    @ApiModelProperty(value = "书名")
    private String name;

    @ApiModelProperty(value = "isbn号")
    private String isbn;

    @ApiModelProperty(value = "简要说明")
    private String note;

}
