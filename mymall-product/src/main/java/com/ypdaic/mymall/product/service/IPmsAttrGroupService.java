package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.product.entity.PmsAttrGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.PmsAttrGroupDto;
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
 * @since 2020-06-07
 */
public interface IPmsAttrGroupService extends IService<PmsAttrGroup> {

    /**
     * 新增属性分组
     * @param pmsAttrGroupDto
     * @return
     */
    PmsAttrGroup add(PmsAttrGroupDto pmsAttrGroupDto);

    /**
     * 更新属性分组
     * @param pmsAttrGroupDto
     * @return
     */
    PmsAttrGroup update(PmsAttrGroupDto pmsAttrGroupDto);

    /**
     * 删除属性分组
     * @param pmsAttrGroupDto
     * @return
     */
    PmsAttrGroup delete(PmsAttrGroupDto pmsAttrGroupDto);

    /**
     * 分页查询属性分组
     * @param pmsAttrGroupDto
     * @param pmsAttrGroupPage
     * @return
     */
    IPage<PmsAttrGroup> queryPage(PmsAttrGroupDto pmsAttrGroupDto, Page<PmsAttrGroup> pmsAttrGroupPage);


    /**
     * 校验属性分组名称
     * @param pmsAttrGroupDto
     * @return
     */
    boolean checkName(PmsAttrGroupDto pmsAttrGroupDto, boolean isAdd);

    /**
     *
     * 查询所有属性分组
     * @return
     */
    List<PmsAttrGroup> queryAll(PmsAttrGroupDto pmsAttrGroupDto);
}
