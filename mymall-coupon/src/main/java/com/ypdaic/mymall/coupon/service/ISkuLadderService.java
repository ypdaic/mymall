package com.ypdaic.mymall.coupon.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.coupon.entity.SkuLadder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.coupon.vo.SkuLadderDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 商品阶梯价格 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface ISkuLadderService extends IService<SkuLadder> {

    /**
     * 新增商品阶梯价格
     * @param skuLadderDto
     * @return
     */
    SkuLadder add(SkuLadderDto skuLadderDto);

    /**
     * 更新商品阶梯价格
     * @param skuLadderDto
     * @return
     */
    SkuLadder update(SkuLadderDto skuLadderDto);

    /**
     * 删除商品阶梯价格
     * @param skuLadderDto
     * @return
     */
    SkuLadder delete(SkuLadderDto skuLadderDto);

    /**
     * 分页查询商品阶梯价格
     * @param skuLadderDto
     * @param skuLadderPage
     * @return
     */
    IPage<SkuLadder> queryPage(SkuLadderDto skuLadderDto, Page<SkuLadder> skuLadderPage);


    /**
     * 校验商品阶梯价格名称
     * @param skuLadderDto
     * @return
     */
    boolean checkName(SkuLadderDto skuLadderDto, boolean isAdd);

    /**
     *
     * 查询所有商品阶梯价格
     * @return
     */
    List<SkuLadder> queryAll(SkuLadderDto skuLadderDto);

    PageUtils queryPage(Map<String, Object> params);
}
