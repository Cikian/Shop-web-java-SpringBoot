package com.ci.controller;

import com.ci.pojo.entity.Good;
import com.ci.pojo.entity.User;
import com.ci.pojo.vo.ErrorCode;
import com.ci.pojo.vo.GoodView;
import com.ci.pojo.vo.Result;
import com.ci.service.CateService;
import com.ci.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    // 获取首页推荐商品
    @GetMapping("/rec")
    public Result getRec() {
        List<Good> recGoods = goodService.getAllRecGoods();
        Integer code = recGoods != null ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
        String msg = recGoods != null ? "" : "查询失败";
        return new Result(code, recGoods, msg);
    }

    @GetMapping("/order/{order}")
    public Result getAllByOrder(@PathVariable String order) {
        switch (order) {
            case "a": {
                List<Good> allGoods = goodService.getAll();
                Integer code = allGoods != null ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
                String msg = allGoods != null ? "" : "查询失败";
                return new Result(code, allGoods, msg);
            }
            case "up": {
                List<Good> allGoods = goodService.getAllByOrderUp();
                Integer code = allGoods != null ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
                String msg = allGoods != null ? "" : "查询失败";
                return new Result(code, allGoods, msg);
            }
            case "down": {
                List<Good> allGoods = goodService.getAllByOrderDown();
                Integer code = allGoods != null ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
                String msg = allGoods != null ? "" : "查询失败";
                return new Result(code, allGoods, msg);
            }
            default:
                return new Result(ErrorCode.GET_FAIL, null, "查询失败");
        }
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

    @GetMapping("/search/{keyword}")
    public Result search(@PathVariable String keyword) {
        List<Good> goods = goodService.getGoodsByKeyWord(keyword);
        Integer code = goods != null ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
        String msg = goods != null ? "" : "查询失败";
        return new Result(code, goods, msg);
    }

    @GetMapping("/cate/{cateId}/{UoD}")
    public Result getByCateId(@PathVariable String cateId, @PathVariable String UoD) {
        switch (UoD) {
            case "a": {
                List<Good> goods = goodService.getGoodsByCateId(cateId);
                Integer code = goods != null ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
                String msg = goods != null ? "" : "查询失败";
                return new Result(code, goods, msg);
            }
            case "up": {
                List<Good> goods = goodService.getGoodsByCateIdUp(cateId);
                Integer code = goods != null ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
                String msg = goods != null ? "" : "查询失败";
                return new Result(code, goods, msg);
            }
            case "down": {
                List<Good> goods = goodService.getGoodsByCateIdDown(cateId);
                Integer code = goods != null ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
                String msg = goods != null ? "" : "查询失败";
                return new Result(code, goods, msg);
            }
            default:
                return new Result(ErrorCode.GET_FAIL, null, "查询失败");
        }
    }




    @GetMapping("/test")
    public User test(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (User) session.getAttribute("user");
    }



}
