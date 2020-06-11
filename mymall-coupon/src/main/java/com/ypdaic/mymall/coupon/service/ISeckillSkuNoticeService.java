package com.ypdaic.mymall.coupon.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.coupon.entity.SeckillSkuNotice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.coupon.vo.SeckillSkuNoticeDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 秒杀商品通知订阅 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface ISeckillSkuNoticeService extends IService<SeckillSkuNotice> {

    /**
     * 新增秒杀商品通知订阅
     * @param seckillSkuNoticeDto
     * @return
     */
    SeckillSkuNotice add(SeckillSkuNoticeDto seckillSkuNoticeDto);

    /**
     * 更新秒杀商品通知订阅
     * @param seckillSkuNoticeDto
     * @return
     */
    SeckillSkuNotice update(SeckillSkuNoticeDto seckillSkuNoticeDto);

    /**
     * 删除秒杀商品通知订阅
     * @param seckillSkuNoticeDto
     * @return
     */
    SeckillSkuNotice delete(SeckillSkuNoticeDto seckillSkuNoticeDto);

    /**
     * 分页查询秒杀商品通知订阅
     * @param seckillSkuNoticeDto
     * @param seckillSkuNoticePage
     * @return
     */
    IPage<SeckillSkuNotice> queryPage(SeckillSkuNoticeDto seckillSkuNoticeDto, Page<SeckillSkuNotice> seckillSkuNoticePage);


    /**
     * 校验秒杀商品通知订阅名称
     * @param seckillSkuNoticeDto
     * @return
     */
    boolean checkName(SeckillSkuNoticeDto seckillSkuNoticeDto, boolean isAdd);

    /**
     *
     * 查询所有秒杀商品通知订阅
     * @return
     */
    List<SeckillSkuNotice> queryAll(SeckillSkuNoticeDto seckillSkuNoticeDto);

    PageUtils queryPage(Map<String, Object> params);
}
