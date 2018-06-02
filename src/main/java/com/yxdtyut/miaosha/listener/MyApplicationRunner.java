package com.yxdtyut.miaosha.listener;

import com.yxdtyut.miaosha.key.GoodsKey;
import com.yxdtyut.miaosha.service.GoodsService;
import com.yxdtyut.miaosha.service.RedisService;
import com.yxdtyut.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author : yangxudong
 * @Description : 初始化的一些操作
 * @Date : 下午2:11 2018/5/30
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Autowired
    private RedisService redisService;

    @Autowired
    private GoodsService goodsService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
