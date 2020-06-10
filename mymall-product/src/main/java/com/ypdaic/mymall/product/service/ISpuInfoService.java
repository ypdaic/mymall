package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.product.entity.SpuInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.SpuInfoDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ypdaic.mymall.product.vo.SpuSaveVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * spu信息 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface ISpuInfoService extends IService<SpuInfo> {

    /**
     * 新增spu信息
     * @param spuInfoDto
     * @return
     */
    SpuInfo add(SpuInfoDto spuInfoDto);

    /**
     * 更新spu信息
     * @param spuInfoDto
     * @return
     */
    SpuInfo update(SpuInfoDto spuInfoDto);

    /**
     * 删除spu信息
     * @param spuInfoDto
     * @return
     */
    SpuInfo delete(SpuInfoDto spuInfoDto);

    /**
     * 分页查询spu信息
     * @param spuInfoDto
     * @param spuInfoPage
     * @return
     */
    IPage<SpuInfo> queryPage(SpuInfoDto spuInfoDto, Page<SpuInfo> spuInfoPage);


    /**
     * 校验spu信息名称
     * @param spuInfoDto
     * @return
     */
    boolean checkName(SpuInfoDto spuInfoDto, boolean isAdd);

    /**
     *
     * 查询所有spu信息
     * @return
     */
    List<SpuInfo> queryAll(SpuInfoDto spuInfoDto);

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfo(SpuSaveVo vo);

    void saveBaseSpuInfo(SpuInfo infoEntity);


    PageUtils queryPageByCondition(Map<String, Object> params);


}
