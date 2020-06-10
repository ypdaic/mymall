package com.ypdaic.mymall.coupon.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.coupon.entity.SpuBounds;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.coupon.vo.SpuBoundsDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 商品spu积分设置 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-10
 */
public interface ISpuBoundsService extends IService<SpuBounds> {

    /**
     * 新增商品spu积分设置
     * @param spuBoundsDto
     * @return
     */
    SpuBounds add(SpuBoundsDto spuBoundsDto);

    /**
     * 更新商品spu积分设置
     * @param spuBoundsDto
     * @return
     */
    SpuBounds update(SpuBoundsDto spuBoundsDto);

    /**
     * 删除商品spu积分设置
     * @param spuBoundsDto
     * @return
     */
    SpuBounds delete(SpuBoundsDto spuBoundsDto);

    /**
     * 分页查询商品spu积分设置
     * @param spuBoundsDto
     * @param spuBoundsPage
     * @return
     */
    IPage<SpuBounds> queryPage(SpuBoundsDto spuBoundsDto, Page<SpuBounds> spuBoundsPage);


    /**
     * 校验商品spu积分设置名称
     * @param spuBoundsDto
     * @return
     */
    boolean checkName(SpuBoundsDto spuBoundsDto, boolean isAdd);

    /**
     *
     * 查询所有商品spu积分设置
     * @return
     */
    List<SpuBounds> queryAll(SpuBoundsDto spuBoundsDto);

    PageUtils queryPage(Map<String, Object> params);
}
