package com.ypdaic.mymall.coupon.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.coupon.entity.SeckillSkuRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.coupon.vo.SeckillSkuRelationDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 秒杀活动商品关联 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface ISeckillSkuRelationService extends IService<SeckillSkuRelation> {

    /**
     * 新增秒杀活动商品关联
     * @param seckillSkuRelationDto
     * @return
     */
    SeckillSkuRelation add(SeckillSkuRelationDto seckillSkuRelationDto);

    /**
     * 更新秒杀活动商品关联
     * @param seckillSkuRelationDto
     * @return
     */
    SeckillSkuRelation update(SeckillSkuRelationDto seckillSkuRelationDto);

    /**
     * 删除秒杀活动商品关联
     * @param seckillSkuRelationDto
     * @return
     */
    SeckillSkuRelation delete(SeckillSkuRelationDto seckillSkuRelationDto);

    /**
     * 分页查询秒杀活动商品关联
     * @param seckillSkuRelationDto
     * @param seckillSkuRelationPage
     * @return
     */
    IPage<SeckillSkuRelation> queryPage(SeckillSkuRelationDto seckillSkuRelationDto, Page<SeckillSkuRelation> seckillSkuRelationPage);


    /**
     * 校验秒杀活动商品关联名称
     * @param seckillSkuRelationDto
     * @return
     */
    boolean checkName(SeckillSkuRelationDto seckillSkuRelationDto, boolean isAdd);

    /**
     *
     * 查询所有秒杀活动商品关联
     * @return
     */
    List<SeckillSkuRelation> queryAll(SeckillSkuRelationDto seckillSkuRelationDto);

    PageUtils queryPage(Map<String, Object> params);
}
