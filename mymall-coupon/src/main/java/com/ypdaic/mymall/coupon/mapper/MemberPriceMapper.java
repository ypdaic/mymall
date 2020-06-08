package com.ypdaic.mymall.coupon.mapper;

import com.ypdaic.mymall.coupon.entity.MemberPrice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.coupon.vo.MemberPriceDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 商品会员价格 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface MemberPriceMapper extends BaseMapper<MemberPrice> {

    /**
     * 分页查询商品会员价格
     * @param memberPricePage
     * @param memberPriceDto
     * @return
     */
    IPage<MemberPrice> queryPage(Page<MemberPrice> memberPricePage, @Param("dto") MemberPriceDto memberPriceDto);

    /**
     * 导出查询数量
     * @param memberPriceDto
     * @return
     */
    Integer queryCount(@Param("dto") MemberPriceDto memberPriceDto);

    /**
     * 导出分页查询商品会员价格
     * @param memberPricePage
     * @param memberPriceDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> memberPricePage, @Param("dto") MemberPriceDto memberPriceDto);

    /**
     *
     * 查询所有商品会员价格
     * @return
     */
    List<MemberPrice> queryAll(@Param("dto") MemberPriceDto memberPriceDto);

}
