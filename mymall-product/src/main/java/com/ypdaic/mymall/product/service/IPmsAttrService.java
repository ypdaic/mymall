package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.product.entity.PmsAttr;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.PmsAttrDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 商品属性 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public interface IPmsAttrService extends IService<PmsAttr> {

    /**
     * 新增商品属性
     * @param pmsAttrDto
     * @return
     */
    PmsAttr add(PmsAttrDto pmsAttrDto);

    /**
     * 更新商品属性
     * @param pmsAttrDto
     * @return
     */
    PmsAttr update(PmsAttrDto pmsAttrDto);

    /**
     * 删除商品属性
     * @param pmsAttrDto
     * @return
     */
    PmsAttr delete(PmsAttrDto pmsAttrDto);

    /**
     * 分页查询商品属性
     * @param pmsAttrDto
     * @param pmsAttrPage
     * @return
     */
    IPage<PmsAttr> queryPage(PmsAttrDto pmsAttrDto, Page<PmsAttr> pmsAttrPage);


    /**
     * 校验商品属性名称
     * @param pmsAttrDto
     * @return
     */
    boolean checkName(PmsAttrDto pmsAttrDto, boolean isAdd);

    /**
     *
     * 查询所有商品属性
     * @return
     */
    List<PmsAttr> queryAll(PmsAttrDto pmsAttrDto);
}
