package com.yxdtyut.miaosha.result;

import lombok.Data;
import lombok.Getter;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author : yangxudong
 * @Description :   返回结果的封装类
 * @Date : 下午3:03 2018/5/29
 */
@Getter
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public Result(T data) {
        this.code = 200;
        this.msg = "成功";
        this.data = data;
    }

    public Result(CodeMsg codeMsg) {
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }

    /**成功时候调用的方法.*/
    public static <T> Result<T> success(T data, HttpServletResponse response) {
        return new Result<T>(data);
    }

    /**失败时候调用的方法.*/
    public static <T> Result<T> error(CodeMsg codeMsg) {
        return new Result<T>(codeMsg);
    }
}
