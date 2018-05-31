package com.yxdtyut.miaosha.exception;

import com.yxdtyut.miaosha.result.CodeMsg;
import lombok.Data;
import lombok.Getter;

/**
 * @Author : yangxudong
 * @Description :   用户异常
 * @Date : 下午4:24 2018/5/29
 */
@Getter
public class GlobleException extends RuntimeException {
    private CodeMsg cm;

    public GlobleException(CodeMsg cm) {
        super(cm.getMsg());
        this.cm = cm;
    }
}
