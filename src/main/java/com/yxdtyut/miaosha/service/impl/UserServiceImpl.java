package com.yxdtyut.miaosha.service.impl;

import com.yxdtyut.miaosha.common.CommonParams;
import com.yxdtyut.miaosha.common.UserContext;
import com.yxdtyut.miaosha.entity.MiaoshaUser;
import com.yxdtyut.miaosha.exception.GlobleException;
import com.yxdtyut.miaosha.key.UserKey;
import com.yxdtyut.miaosha.mapper.UserMapper;
import com.yxdtyut.miaosha.result.CodeMsg;
import com.yxdtyut.miaosha.service.RedisService;
import com.yxdtyut.miaosha.service.UserService;
import com.yxdtyut.miaosha.util.MD5Utils;
import com.yxdtyut.miaosha.util.UUIDUtils;
import com.yxdtyut.miaosha.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午4:41 2018/5/29
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;


    @Override
    public Boolean login(LoginVo loginVo, HttpServletResponse response) {
        String userId = loginVo.getMobile();
        String password = loginVo.getPassword();
        MiaoshaUser user = userMapper.getMiaoshaUserByUserId(userId);
        if (user == null) {
            log.warn(CodeMsg.USER_NOT_EXIST.getMsg());
            throw new GlobleException(CodeMsg.USER_NOT_EXIST);
        }
        if (!MD5Utils.formPassToDBPass(password, user.getSalt()).equals(user.getPassword())) {
            log.warn(CodeMsg.PWD_NOT_RIGHT.getMsg());
            throw new GlobleException(CodeMsg.PWD_NOT_RIGHT);
        }

        String cookieToken = UUIDUtils.uuid();
        redisService.set(UserKey.token, cookieToken, user);
        saveMiaoshaUserToCookies(response,user,cookieToken);
        return true;
    }

    private void saveMiaoshaUserToCookies(HttpServletResponse response, MiaoshaUser user, String cookieToken) {
        Cookie cookie = new Cookie(CommonParams.COOKIE_NAME, cookieToken);
        cookie.setMaxAge(UserKey.token.getExpireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
