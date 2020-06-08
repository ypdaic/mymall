package com.ypdaic.mymall.coupon.service;

import com.ypdaic.mymall.coupon.entity.SeckillPromotion;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.coupon.vo.SeckillPromotionDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 秒杀活动 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface ISeckillPromotionService extends IService<SeckillPromotion> {

    /**
     * 新增秒杀活动
     * @param seckillPromotionDto
     * @return
     */
    SeckillPromotion add(SeckillPromotionDto seckillPromotionDto);

    /**
     * 更新秒杀活动
     * @param seckillPromotionDto
     * @return
     */
    SeckillPromotion update(SeckillPromotionDto seckillPromotionDto);

    /**
     * 删除秒杀活动
     * @param seckillPromotionDto
     * @return
     */
    SeckillPromotion delete(SeckillPromotionDto seckillPromotionDto);

    /**
     * 分页查询秒杀活动
     * @param seckillPromotionDto
     * @param seckillPromotionPage
     * @return
     */
    IPage<SeckillPromotion> queryPage(SeckillPromotionDto seckillPromotionDto, Page<SeckillPromotion> seckillPromotionPage);


    /**
     * 校验秒杀活动名称
     * @param seckillPromotionDto
     * @return
     */
    boolean checkName(SeckillPromotionDto seckillPromotionDto, boolean isAdd);

    /**
     *
     * 查询所有秒杀活动
     * @return
     */
    List<SeckillPromotion> queryAll(SeckillPromotionDto seckillPromotionDto);
}
