package com.yxdtyut.miaosha.service.impl;

import com.yxdtyut.miaosha.mapper.GoodsMapper;
import com.yxdtyut.miaosha.service.GoodsService;
import com.yxdtyut.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : yangxudong
 * @Description :   商品serviceImpl
 * @Date : 下午2:35 2018/5/30
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsVo> listGoodsVo() {
        return goodsMapper.listGoodsVo();
    }
}
