package com.ci.util;

import com.UpYun;
import com.ci.exception.SystemException;
import com.ci.pojo.vo.ErrorCode;
import com.ci.pojo.vo.Result;
import com.upyun.UpException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class FileUtils {
    public static Result uploadToUpYun(MultipartFile file, String userId) {
        System.out.println("图片>>>>>>>>>>>>>>>>>>>>" + file);
        System.out.println("用户id>>>>>>>>>>>>>>>>>>>>" + userId);
        String filePath;
        // 获取文件拓展名
        String fileExt = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
        System.out.println("文件拓展名>>>>>>>>>>>>>>>>>>>>" + fileExt);
        if (userId == null) {
            filePath = "/develop/Shop-dev/avatar/" + UUID.randomUUID() + "." + fileExt;
        } else {
            filePath = "/develop/Shop-dev/avatar/" + userId + "avatar" + fileExt;
        }

        UpYun upYun = new UpYun("cikian", "cikian", "D1rD3YcbZgkluT7A8SkTYAow6DE2Y3aY");
        System.out.println("图片名称：" + "https://img-upyun.cikian.cn/" + "." + filePath);
        boolean res;
        try {
            res = upYun.writeFile(filePath, file.getBytes(), true);
        } catch (IOException | UpException e) {
            throw new SystemException(ErrorCode.SYSTEM_ERR, "系统繁忙，请稍后再试", e);
        }
        // 创建一个map集合存储返图片路径
        if (!res) {
            return new Result(ErrorCode.COMMON_FAIL, null, "上传失败");
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("filePath", "https://img-upyun.cikian.cn/" + filePath);
        return new Result(ErrorCode.COMMON_SUCCESS, map, "上传成功");
    }
}
