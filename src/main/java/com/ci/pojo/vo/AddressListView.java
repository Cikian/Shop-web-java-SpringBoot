package com.ci.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/10/9 20:17
 */

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddressListView {
    private String id;
    private String name;
    private String tel;
    private String address;
    private String areaCode;
    private boolean isDefault;

    @JsonProperty(value = "isDefault")
    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
