package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.product.entity.AttrGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.AttrGroupDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 属性分组 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IAttrGroupService extends IService<AttrGroup> {

    /**
     * 新增属性分组
     * @param attrGroupDto
     * @return
     */
    AttrGroup add(AttrGroupDto attrGroupDto);

    /**
     * 更新属性分组
     * @param attrGroupDto
     * @return
     */
    AttrGroup update(AttrGroupDto attrGroupDto);

    /**
     * 删除属性分组
     * @param attrGroupDto
     * @return
     */
    AttrGroup delete(AttrGroupDto attrGroupDto);

    /**
     * 分页查询属性分组
     * @param attrGroupDto
     * @param attrGroupPage
     * @return
     */
    IPage<AttrGroup> queryPage(AttrGroupDto attrGroupDto, Page<AttrGroup> attrGroupPage);


    /**
     * 校验属性分组名称
     * @param attrGroupDto
     * @return
     */
    boolean checkName(AttrGroupDto attrGroupDto, boolean isAdd);

    /**
     *
     * 查询所有属性分组
     * @return
     */
    List<AttrGroup> queryAll(AttrGroupDto attrGroupDto);
}
