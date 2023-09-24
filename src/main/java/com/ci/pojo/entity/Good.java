package com.ci.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("goods")
public class Good {
    @TableId(type = IdType.ASSIGN_ID)
    private String goodId;

    private String cateId;

    @NotBlank(message = "商品名不能为空")
    private String name;

    @NotBlank(message = "副标题不能为空")
    @TableField("sub_title")
    private String subtitle;  // 副标题

    private String mainImage;  // 主图

    private String subImages;  // 子图 json格式

    @NotBlank(message = "商品详情介绍不能为空")
    private String detail;  // 商品详情

    @NotEmpty(message = "价格不能为空")
    private Double price;  // 价格

    @NotEmpty(message = "库存不能为空")
    private Integer stock;  // 库存

    @NotEmpty(message = "商品状态不能为空")
    private Integer status;  // 商品状态 1:在售 2:下架 3:删除
    private String createTime;
    private String updateTime;
}
