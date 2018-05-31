package com.yxdtyut.miaosha.controller;

import com.yxdtyut.miaosha.annotation.UserAccess;
import com.yxdtyut.miaosha.entity.MiaoshaUser;
import com.yxdtyut.miaosha.service.GoodsService;
import com.yxdtyut.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author : yangxudong
 * @Description :   商品controller
 * @Date : 下午1:45 2018/5/30
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/to_list")
    @UserAccess
    public String toList(MiaoshaUser user, Model model){
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("user", user);
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }

}
