package com.yxdtyut.miaosha.service;


import com.yxdtyut.miaosha.entity.MiaoshaUser;
import com.yxdtyut.miaosha.vo.LoginVo;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author : yangxudong
 * @Description :   用户service
 * @Date : 下午4:20 2018/5/29
 */
public interface UserService {

    Boolean login(LoginVo loginVo, HttpServletResponse response);
}
