package com.yxdtyut.miaosha.result;

import lombok.Getter;

/**
 * @Author : yangxudong
 * @Description :   错误码
 * @Date : 下午3:04 2018/5/29
 */
@Getter
public class CodeMsg {
    /**通用模块.*/
    public static final CodeMsg SUCCESS = new CodeMsg(200, "成功");
    public static final CodeMsg SERVER_ERROR = new CodeMsg(500, "服务异常");

    /**用户模块.5001xx*/
    public static final CodeMsg USER_NOT_EXIST = new CodeMsg(500100, "用户不存在");
    public static final CodeMsg PWD_NOT_RIGHT = new CodeMsg(500101, "登陆密码不正确");
    public static final CodeMsg USER_NOT_LOGIN = new CodeMsg(500102, "请先登陆");
    public static final CodeMsg OPERATION_FREQUENT = new CodeMsg(500103, "操作太频繁，请稍后重试");

    /**商品模块.5002XX*/
    public static final CodeMsg STOCK_NOT_ENUOUGH= new CodeMsg(500200, "商品库存不足");

    /**秒杀模块5003xx.*/
    public static final CodeMsg REPEAT_MIAOSHA= new CodeMsg(500300, "重复秒杀");
    public static final CodeMsg VERIFYCODE_NOT_RIGHT= new CodeMsg(500301, "验证码不正确");

    /**订单模块5004xx.*/
    public static final CodeMsg ORDER_NOT_EXIST= new CodeMsg(500400, "订单不存在");


    private Integer code;
    private String msg;

    private CodeMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private CodeMsg() {
    }


}
