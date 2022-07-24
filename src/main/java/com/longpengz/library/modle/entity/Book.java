package com.longpengz.library.modle.entity;

import com.longpengz.dataprocessing.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Book",description = "书本实体")
public class Book extends BaseEntity {

    @Column(columnDefinition = "varchar(50) comment '书名'")
    @ApiModelProperty(value = "书名")
    private String name;

    @Column(columnDefinition = "varchar(50) comment 'isbn号'")
    @ApiModelProperty(value = "isbn号")
    private String isbn;

    @Column(columnDefinition = "varchar(255) comment '简要说明'")
    @ApiModelProperty(value = "简要说明")
    private String note;

    @Column(columnDefinition = "decimal(19,2) comment '单价'")
    @ApiModelProperty(value = "单价")
    private BigDecimal price;

    @Column(columnDefinition = "varchar(255) comment '索书号'")
    @ApiModelProperty(value = "索书号")
    private String callNumber;

    @Column(columnDefinition = "varchar(255) comment '分类号'")
    @ApiModelProperty(value = "分类号")
    private String classificationNumber;

    @Column(columnDefinition = "varchar(255) comment '出版者'")
    @ApiModelProperty(value = "出版者")
    private String publisher;

    @Column(columnDefinition = "longtext comment 'marc'")
    @ApiModelProperty(value = "marc")
    private String marc;

}
