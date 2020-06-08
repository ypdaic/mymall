package com.ypdaic.mymall.ware.mapper;

import com.ypdaic.mymall.ware.entity.WareInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.ware.vo.WareInfoDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 仓库信息 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface WareInfoMapper extends BaseMapper<WareInfo> {

    /**
     * 分页查询仓库信息
     * @param wareInfoPage
     * @param wareInfoDto
     * @return
     */
    IPage<WareInfo> queryPage(Page<WareInfo> wareInfoPage, @Param("dto") WareInfoDto wareInfoDto);

    /**
     * 导出查询数量
     * @param wareInfoDto
     * @return
     */
    Integer queryCount(@Param("dto") WareInfoDto wareInfoDto);

    /**
     * 导出分页查询仓库信息
     * @param wareInfoPage
     * @param wareInfoDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> wareInfoPage, @Param("dto") WareInfoDto wareInfoDto);

    /**
     *
     * 查询所有仓库信息
     * @return
     */
    List<WareInfo> queryAll(@Param("dto") WareInfoDto wareInfoDto);

}
