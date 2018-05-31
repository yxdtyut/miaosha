package com.yxdtyut.miaosha.mapper;

import com.yxdtyut.miaosha.entity.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午4:42 2018/5/29
 */
@Mapper
public interface UserMapper {

    MiaoshaUser getMiaoshaUserByUserId(@Param("id") String userId);
}
