package com.yxdtyut.miaosha.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author : yangxudong
 * @Description :   用户pojo
 * @Date : 上午10:40 2018/5/29
 */
@Data
public class MiaoshaUser {
    /**用户id,手机号码.*/
    private String id;

    /**昵称.*/
    private String nickname;

    /**密码(两次MD5加密之后的密码).*/
    private String password;

    /**加密的盐.*/
    private String salt;

    /**头像，云存储id.*/
    private String head;

    /**注册时间.*/
    private Date registerDate;

    /**上次登陆时间.*/
    private Date lastLoginDate;

    /**登陆次数.*/
    private Integer loginCount;

}
