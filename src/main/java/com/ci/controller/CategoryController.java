package com.ci.controller;

import com.ci.pojo.vo.CateView;
import com.ci.pojo.vo.ErrorCode;
import com.ci.pojo.vo.Result;
import com.ci.service.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cate")
public class CategoryController {
    @Autowired
    CateService cateService;
    @GetMapping
    public Result getAllCateView() {
        List<CateView> cateViews = cateService.selectAllCateView();
        String msg = cateViews != null ? "查询成功" : "查询失败";
        Integer code = cateViews != null ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
        return new Result(code, cateViews, msg);
    }
}
