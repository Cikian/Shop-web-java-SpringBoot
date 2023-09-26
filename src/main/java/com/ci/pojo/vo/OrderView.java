package com.ci.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ci.pojo.entity.OrderItem;
import com.ci.pojo.entity.Shopping;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/9/26 10:15
 */

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderView {
    @TableId(type = IdType.ASSIGN_ID)
    private String orderId;
    @NotNull
    private String userId;

    @Getter
    @NotNull
    private Shopping shopping;  // 收货信息

    @Getter
    @NotNull
    @NotEmpty
    private List<OrderItem> orderItemList;  // 订单商品

    @NotNull
    private Double payment;     // 订单金额

    @NotNull
    private Integer paymentType; // 支付类型：1-在线支付
    private Integer postPrice;  // 运费

    public Shopping getShopping() {
        return shopping;
    }

    public void setShopping(Shopping shopping) {
        this.shopping = shopping;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
