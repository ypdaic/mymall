package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.product.entity.PmsSpuInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.PmsSpuInfoDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * spu信息 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public interface IPmsSpuInfoService extends IService<PmsSpuInfo> {

    /**
     * 新增spu信息
     * @param pmsSpuInfoDto
     * @return
     */
    PmsSpuInfo add(PmsSpuInfoDto pmsSpuInfoDto);

    /**
     * 更新spu信息
     * @param pmsSpuInfoDto
     * @return
     */
    PmsSpuInfo update(PmsSpuInfoDto pmsSpuInfoDto);

    /**
     * 删除spu信息
     * @param pmsSpuInfoDto
     * @return
     */
    PmsSpuInfo delete(PmsSpuInfoDto pmsSpuInfoDto);

    /**
     * 分页查询spu信息
     * @param pmsSpuInfoDto
     * @param pmsSpuInfoPage
     * @return
     */
    IPage<PmsSpuInfo> queryPage(PmsSpuInfoDto pmsSpuInfoDto, Page<PmsSpuInfo> pmsSpuInfoPage);


    /**
     * 校验spu信息名称
     * @param pmsSpuInfoDto
     * @return
     */
    boolean checkName(PmsSpuInfoDto pmsSpuInfoDto, boolean isAdd);

    /**
     *
     * 查询所有spu信息
     * @return
     */
    List<PmsSpuInfo> queryAll(PmsSpuInfoDto pmsSpuInfoDto);
}
