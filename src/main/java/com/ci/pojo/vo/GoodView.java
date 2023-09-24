package com.ci.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("goods")
public class GoodView {
    @TableId(type = IdType.ASSIGN_ID)
    private String goodId;

    private String cateName;

    @NotBlank(message = "商品名不能为空")
    private String name;

    @NotBlank(message = "副标题不能为空")
    private String subtitle;  // 副标题

    private String mainImage;  // 主图

    private String subImages;  // 子图 json格式

    @NotBlank(message = "商品详情介绍不能为空")
    private String detail;  // 商品详情

    @NotNull(message = "价格不能为空")
    private Double price;  // 价格

    @NotNull(message = "库存不能为空")
    private Integer stock;  // 库存

    @NotNull(message = "商品状态不能为空")
    private Integer status;  // 商品状态 1:在售 2:下架 3:删除
}
