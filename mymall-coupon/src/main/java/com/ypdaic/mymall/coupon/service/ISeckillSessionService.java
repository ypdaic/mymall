package com.ypdaic.mymall.coupon.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.coupon.entity.SeckillSession;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.coupon.vo.SeckillSessionDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 秒杀活动场次 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface ISeckillSessionService extends IService<SeckillSession> {

    /**
     * 新增秒杀活动场次
     * @param seckillSessionDto
     * @return
     */
    SeckillSession add(SeckillSessionDto seckillSessionDto);

    /**
     * 更新秒杀活动场次
     * @param seckillSessionDto
     * @return
     */
    SeckillSession update(SeckillSessionDto seckillSessionDto);

    /**
     * 删除秒杀活动场次
     * @param seckillSessionDto
     * @return
     */
    SeckillSession delete(SeckillSessionDto seckillSessionDto);

    /**
     * 分页查询秒杀活动场次
     * @param seckillSessionDto
     * @param seckillSessionPage
     * @return
     */
    IPage<SeckillSession> queryPage(SeckillSessionDto seckillSessionDto, Page<SeckillSession> seckillSessionPage);


    /**
     * 校验秒杀活动场次名称
     * @param seckillSessionDto
     * @return
     */
    boolean checkName(SeckillSessionDto seckillSessionDto, boolean isAdd);

    /**
     *
     * 查询所有秒杀活动场次
     * @return
     */
    List<SeckillSession> queryAll(SeckillSessionDto seckillSessionDto);

    PageUtils queryPage(Map<String, Object> params);
}
