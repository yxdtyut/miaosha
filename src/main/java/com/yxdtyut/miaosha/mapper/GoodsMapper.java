package com.yxdtyut.miaosha.mapper;

import com.yxdtyut.miaosha.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午2:38 2018/5/30
 */
@Mapper
public interface GoodsMapper {
    List<GoodsVo> listGoodsVo();

    GoodsVo getGoodsVoByGoodsId(@Param("goodsId") String goodsId);

    Integer reduceMiaoshaGoodsStock(@Param("goodsId") String goodsId);
}
