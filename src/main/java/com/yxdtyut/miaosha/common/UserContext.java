package com.yxdtyut.miaosha.common;

import com.yxdtyut.miaosha.entity.MiaoshaUser;
import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :   线程级别安全的类，存储用户信息
 * @Date : 下午2:06 2018/5/30
 */
public class UserContext {
    private static ThreadLocal<MiaoshaUser> context = new ThreadLocal();

    public static MiaoshaUser getMiaoshaUser() {
        return context.get();
    }

    public static void setMiaoshaUser(MiaoshaUser miaoshaUser) {
        context.set(miaoshaUser);
    }
}
