package com.yxdtyut.miaosha.mapper;

import com.yxdtyut.miaosha.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午2:38 2018/5/30
 */
@Mapper
public interface GoodsMapper {
    List<GoodsVo> listGoodsVo();
}
