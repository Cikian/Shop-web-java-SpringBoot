package com.ci.controller;

import com.ci.pojo.entity.Good;
import com.ci.pojo.vo.ErrorCode;
import com.ci.pojo.vo.GoodView;
import com.ci.pojo.vo.Result;
import com.ci.service.CateService;
import com.ci.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Shirley
 * @version 1.0
 * @description: TODO
 * @date 2023/9/12 19:04
 */

@RestController
@RequestMapping("/good")
public class GoodController {
    @Autowired
    GoodService goodService;
    @Autowired
    CateService cateService;

    @GetMapping
    public Result getAll() {
        List<Good> allGoods = goodService.getAll();
        Integer code = allGoods != null ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
        String msg = allGoods != null ? "" : "查询失败";
        return new Result(code, allGoods, msg);
    }

    @GetMapping("/{goodId}")
    public Result getById(@PathVariable("goodId") String goodId) {
        Good good = goodService.getById(goodId);
        Integer code = good != null ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
        String msg = good != null ? "" : "查询失败";
        return new Result(code, good, msg);
    }

    @PostMapping
    public Result addGood(@RequestBody @Valid GoodView goodView, BindingResult bindingResult) {
        System.out.println(goodView);
        for (ObjectError error : bindingResult.getAllErrors()) {
            return new Result(ErrorCode.ADD_FAIL, null, error.getDefaultMessage());
        }
        String cateId = cateService.selectByName(goodView.getCateName()).getCateId();
        Good good = Good.builder()
                .name(goodView.getName())
                .cateId(cateId)
                .subtitle(goodView.getSubtitle())
                .mainImage(goodView.getMainImage())
                .subImages(goodView.getSubImages())
                .detail(goodView.getDetail())
                .price(goodView.getPrice())
                .stock(goodView.getStock())
                .status(goodView.getStatus())
                .build();
        System.out.println("builder的Good对象:" + good);
        boolean flag = goodService.add(good);
        Integer code = flag ? ErrorCode.ADD_SUCCESS : ErrorCode.ADD_FAIL;
        String msg = flag ? "添加成功" : "添加失败";
        return new Result(code, flag, msg);
    }

    @DeleteMapping("/{goodId}")
    public Result deleteGood(@PathVariable String goodId) {
        boolean flag = goodService.deleteGood(goodId);
        Integer code = flag ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
        String msg = flag ? "删除成功" : "删除失败";
        return new Result(code, flag, msg);
    }

    @PutMapping
    public Result updateGood(@RequestBody Good good) {
        boolean flag = goodService.updateGood(good);
        Integer code = flag ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
        String msg = flag ? "更新成功" : "更新失败";
        return new Result(code, flag, msg);
    }

}
