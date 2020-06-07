package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.product.entity.PmsProductAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.PmsProductAttrValueDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * spu属性值 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public interface IPmsProductAttrValueService extends IService<PmsProductAttrValue> {

    /**
     * 新增spu属性值
     * @param pmsProductAttrValueDto
     * @return
     */
    PmsProductAttrValue add(PmsProductAttrValueDto pmsProductAttrValueDto);

    /**
     * 更新spu属性值
     * @param pmsProductAttrValueDto
     * @return
     */
    PmsProductAttrValue update(PmsProductAttrValueDto pmsProductAttrValueDto);

    /**
     * 删除spu属性值
     * @param pmsProductAttrValueDto
     * @return
     */
    PmsProductAttrValue delete(PmsProductAttrValueDto pmsProductAttrValueDto);

    /**
     * 分页查询spu属性值
     * @param pmsProductAttrValueDto
     * @param pmsProductAttrValuePage
     * @return
     */
    IPage<PmsProductAttrValue> queryPage(PmsProductAttrValueDto pmsProductAttrValueDto, Page<PmsProductAttrValue> pmsProductAttrValuePage);


    /**
     * 校验spu属性值名称
     * @param pmsProductAttrValueDto
     * @return
     */
    boolean checkName(PmsProductAttrValueDto pmsProductAttrValueDto, boolean isAdd);

    /**
     *
     * 查询所有spu属性值
     * @return
     */
    List<PmsProductAttrValue> queryAll(PmsProductAttrValueDto pmsProductAttrValueDto);
}
