package com.ci.controller;

import com.ci.pojo.entity.Address;
import com.ci.pojo.entity.User;
import com.ci.pojo.vo.AddressListView;
import com.ci.pojo.vo.ErrorCode;
import com.ci.pojo.vo.Result;
import com.ci.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/10/9 19:43
 */

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping("/item/{id}")
    public Result getById(@PathVariable String id) {
        Address address = addressService.getById(id);
        Integer code = address != null ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
        String msg = address != null ? "查询成功" : "查询失败";
        return new Result(code, address, msg);
    }

    @PutMapping
    public Result add(@RequestBody Address address, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String userId = user.getUserId();
        boolean add = addressService.add(userId, address);
        Integer code = add ? ErrorCode.ADD_SUCCESS : ErrorCode.ADD_FAIL;
        String msg = add ? "添加成功" : "添加失败";
        return new Result(code, null, msg);
    }

    @GetMapping("/{userId}")
    public Result getByUserId(@PathVariable String userId) {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" + userId);
        List<AddressListView> addressList = addressService.getByUserId(userId);
        Integer code = addressList != null ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
        String msg = addressList != null ? "查询成功" : "查询失败";
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" + addressList);
        return new Result(code, addressList, msg);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        boolean delete = addressService.deleteById(id);
        Integer code = delete ? ErrorCode.DELETE_SUCCESS : ErrorCode.DELETE_FAIL;
        String msg = delete ? "删除成功" : "删除失败";
        return new Result(code, null, msg);
    }

    @PostMapping
    public Result updateById(@RequestBody Address address) {
        System.out.println("==============="+address);
        boolean update = addressService.updateById(address);
        Integer code = update ? ErrorCode.UPDATE_SUCCESS : ErrorCode.UPDATE_FAIL;
        String msg = update ? "更新成功" : "更新失败";
        return new Result(code, null, msg);
    }
    
    @GetMapping("/default/{userId}")
    public Result getDefault(@PathVariable String userId) {
        Address address = addressService.selectDefault(userId);
        Integer code = address != null ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
        String msg = address != null ? "查询成功" : "查询失败";
        return new Result(code, address, msg);
    }

}
