package com.ci.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/9/25 14:16
 */

@Data
public class ImageList implements Serializable {
    private List<String> imageUrls;
}
