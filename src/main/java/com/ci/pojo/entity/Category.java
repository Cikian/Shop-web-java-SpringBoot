package com.ci.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @TableId(type = IdType.ASSIGN_ID)
    private String cateId;

    private String parentCateId;

    @NotBlank(message = "分类名不能为空")
    private String name;

    private Integer status;  // 状态 1:正常 2:已废弃

    private Integer sortOrder;  // 排序

    private String updateTime;
}
