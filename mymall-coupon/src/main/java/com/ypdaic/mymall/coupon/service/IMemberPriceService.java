package com.ypdaic.mymall.coupon.service;

import com.ypdaic.mymall.coupon.entity.MemberPrice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.coupon.vo.MemberPriceDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 商品会员价格 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IMemberPriceService extends IService<MemberPrice> {

    /**
     * 新增商品会员价格
     * @param memberPriceDto
     * @return
     */
    MemberPrice add(MemberPriceDto memberPriceDto);

    /**
     * 更新商品会员价格
     * @param memberPriceDto
     * @return
     */
    MemberPrice update(MemberPriceDto memberPriceDto);

    /**
     * 删除商品会员价格
     * @param memberPriceDto
     * @return
     */
    MemberPrice delete(MemberPriceDto memberPriceDto);

    /**
     * 分页查询商品会员价格
     * @param memberPriceDto
     * @param memberPricePage
     * @return
     */
    IPage<MemberPrice> queryPage(MemberPriceDto memberPriceDto, Page<MemberPrice> memberPricePage);


    /**
     * 校验商品会员价格名称
     * @param memberPriceDto
     * @return
     */
    boolean checkName(MemberPriceDto memberPriceDto, boolean isAdd);

    /**
     *
     * 查询所有商品会员价格
     * @return
     */
    List<MemberPrice> queryAll(MemberPriceDto memberPriceDto);
}
