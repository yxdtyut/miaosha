package com.yxdtyut.miaosha.controller;

import com.yxdtyut.miaosha.annotation.Mobile;
import com.yxdtyut.miaosha.annotation.UserAccess;
import com.yxdtyut.miaosha.entity.MiaoshaUser;
import com.yxdtyut.miaosha.key.GoodsKey;
import com.yxdtyut.miaosha.result.Result;
import com.yxdtyut.miaosha.service.GoodsService;
import com.yxdtyut.miaosha.service.RedisService;
import com.yxdtyut.miaosha.vo.GoodsVo;
import com.yxdtyut.miaosha.vo.MiaoshaVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private RedisService redisService;

    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    private ApplicationContext applicationContext;


    /**
     * 做页面缓存
     *
     */
    @GetMapping(value = "/to_list",produces = "text/html")
    @ResponseBody
    public String toList(Model model, HttpServletRequest request,
                         HttpServletResponse response){
        //从缓存中获取页面
        String html = redisService.get(GoodsKey.listPage, "", String.class);
        if (StringUtils.isNotEmpty(html)) {
            return html;
        }
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);
        //缓存页面到redis
        SpringWebContext springWebContext = new SpringWebContext(request,response
                    ,request.getServletContext(),request.getLocale(),model.asMap()
                    ,applicationContext);
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list",springWebContext);
        if (StringUtils.isNotEmpty(html)) {
            redisService.set(GoodsKey.listPage, "", html);
        }
        return html;
        //return "goods_list";
    }

    /**
     * 做url缓存
     * @param goodsId
     * @param user
     * @param model
     * @return
     */
    @GetMapping(value = "/to_detail2/{goodsId}",produces = "text/html")
    @ResponseBody
    public String toDetail2(@PathVariable("goodsId") String goodsId,
                           MiaoshaUser user,
                           Model model, HttpServletRequest request,
                           HttpServletResponse response) {
        //从缓存中获取goods信息
        String html = redisService.get(GoodsKey.detailPage, goodsId, String.class);
        if (null != html) {
            return html;
        }
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        //定义一个秒杀状态标志
        Integer miaoshaStatus = null;
        long remainSeconds = -1;
        long startTime = goodsVo.getStartDate().getTime();
        long endTime = goodsVo.getEndDate().getTime();
        long nowTime = System.currentTimeMillis();
        if (startTime > nowTime) {  //秒杀还未开始
            miaoshaStatus = 0;
            remainSeconds = startTime - nowTime;
        } else if(nowTime > endTime) {  //秒杀已经结束
            miaoshaStatus = 2;
        } else {    //秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("user", user);
        model.addAttribute("goods", goodsVo);
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        //缓存页面到redis
        SpringWebContext springWebContext = new SpringWebContext(request,response
                ,request.getServletContext(),request.getLocale(),model.asMap()
                ,applicationContext);
        html = thymeleafViewResolver.getTemplateEngine().process("goods_detail",springWebContext);
        if (StringUtils.isNotEmpty(html)) {
            redisService.set(GoodsKey.detailPage, goodsId, html);
        }
        return html;
        //return "goods_detail";
    }

    @GetMapping(value = "/detail/{goodsId}")
    @ResponseBody
    public Result<MiaoshaVo> toDetail(@PathVariable("goodsId") String goodsId,
                                      MiaoshaUser user,
                                      Model model, HttpServletRequest request,
                                      HttpServletResponse response) {
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        //定义一个秒杀状态标志
        Integer miaoshaStatus = null;
        long remainSeconds = -1;
        long startTime = goodsVo.getStartDate().getTime();
        long endTime = goodsVo.getEndDate().getTime();
        long nowTime = System.currentTimeMillis();
        if (startTime > nowTime) {  //秒杀还未开始
            miaoshaStatus = 0;
            remainSeconds = startTime - nowTime;
        } else if(nowTime > endTime) {  //秒杀已经结束
            miaoshaStatus = 2;
        } else {    //秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }

        MiaoshaVo miaoshaVo = new MiaoshaVo();
        miaoshaVo.setGoods(goodsVo);
        miaoshaVo.setMiaoshaStatus(miaoshaStatus);
        miaoshaVo.setRemainSeconds(remainSeconds);
        miaoshaVo.setUser(user);
        return Result.success(miaoshaVo);
    }


}
