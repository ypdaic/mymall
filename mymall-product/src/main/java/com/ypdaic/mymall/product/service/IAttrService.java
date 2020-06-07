package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.product.entity.Attr;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.AttrDto;
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
 * @since 2020-06-08
 */
public interface IAttrService extends IService<Attr> {

    /**
     * 新增商品属性
     * @param attrDto
     * @return
     */
    Attr add(AttrDto attrDto);

    /**
     * 更新商品属性
     * @param attrDto
     * @return
     */
    Attr update(AttrDto attrDto);

    /**
     * 删除商品属性
     * @param attrDto
     * @return
     */
    Attr delete(AttrDto attrDto);

    /**
     * 分页查询商品属性
     * @param attrDto
     * @param attrPage
     * @return
     */
    IPage<Attr> queryPage(AttrDto attrDto, Page<Attr> attrPage);


    /**
     * 校验商品属性名称
     * @param attrDto
     * @return
     */
    boolean checkName(AttrDto attrDto, boolean isAdd);

    /**
     *
     * 查询所有商品属性
     * @return
     */
    List<Attr> queryAll(AttrDto attrDto);
}
