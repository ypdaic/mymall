package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.product.entity.SpuInfoDesc;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.SpuInfoDescDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * spu信息介绍 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-10
 */
public interface ISpuInfoDescService extends IService<SpuInfoDesc> {

    /**
     * 新增spu信息介绍
     * @param spuInfoDescDto
     * @return
     */
    SpuInfoDesc add(SpuInfoDescDto spuInfoDescDto);

    /**
     * 更新spu信息介绍
     * @param spuInfoDescDto
     * @return
     */
    SpuInfoDesc update(SpuInfoDescDto spuInfoDescDto);

    /**
     * 删除spu信息介绍
     * @param spuInfoDescDto
     * @return
     */
    SpuInfoDesc delete(SpuInfoDescDto spuInfoDescDto);

    /**
     * 分页查询spu信息介绍
     * @param spuInfoDescDto
     * @param spuInfoDescPage
     * @return
     */
    IPage<SpuInfoDesc> queryPage(SpuInfoDescDto spuInfoDescDto, Page<SpuInfoDesc> spuInfoDescPage);


    /**
     * 校验spu信息介绍名称
     * @param spuInfoDescDto
     * @return
     */
    boolean checkName(SpuInfoDescDto spuInfoDescDto, boolean isAdd);

    /**
     *
     * 查询所有spu信息介绍
     * @return
     */
    List<SpuInfoDesc> queryAll(SpuInfoDescDto spuInfoDescDto);

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfoDesc(SpuInfoDesc descEntity);
}
