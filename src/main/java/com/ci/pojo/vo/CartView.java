package com.ci.pojo.vo;

import lombok.*;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO 购物车视图: 用户ID、商品ID、商品数量
 * @date 2023/9/25 15:37
 */

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CartView {
    private String cardId;
    private String userId;
    private String goodId;
    private Integer count;
    private String name;
    private String subtitle;
    private String mainImage;
    private Double price;
}
