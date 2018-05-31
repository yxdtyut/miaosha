package com.yxdtyut.miaosha.util;

import java.util.UUID;

/**
 * @Author : yangxudong
 * @Description :   uuid工具类
 * @Date : 下午5:21 2018/5/29
 */

public class UUIDUtils {
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
