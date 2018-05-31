package com.yxdtyut.miaosha.exception;

import com.yxdtyut.miaosha.result.CodeMsg;
import com.yxdtyut.miaosha.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author : yangxudong
 * @Description :   异常处理
 * @Date : 下午4:24 2018/5/29
 */
@ControllerAdvice
public class GlobleExceptionHandler {

    @ExceptionHandler(GlobleException.class)
    @ResponseBody
    public Result<String> handler(Exception e) {
        if (e instanceof GlobleException) {
            CodeMsg cm = ((GlobleException) e).getCm();
            return Result.error(cm);
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }

    }

}
