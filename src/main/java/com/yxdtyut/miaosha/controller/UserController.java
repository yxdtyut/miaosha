package com.yxdtyut.miaosha.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.yxdtyut.miaosha.entity.MiaoshaUser;
import com.yxdtyut.miaosha.exception.GlobleException;
import com.yxdtyut.miaosha.result.CodeMsg;
import com.yxdtyut.miaosha.result.Result;
import com.yxdtyut.miaosha.service.RedisService;
import com.yxdtyut.miaosha.service.UserService;
import com.yxdtyut.miaosha.util.MD5Utils;
import com.yxdtyut.miaosha.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午4:15 2018/5/29
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/do_login")
    public Result<Boolean> login(@Valid LoginVo loginVo
            , HttpServletResponse response) {
        Boolean flag =  userService.login(loginVo,response);
        return Result.success(flag, response);
    }
}
