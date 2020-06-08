package com.ypdaic.mymall.ware.mapper;

import com.ypdaic.mymall.ware.entity.WareOrderTaskDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.ware.vo.WareOrderTaskDetailDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 库存工作单 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface WareOrderTaskDetailMapper extends BaseMapper<WareOrderTaskDetail> {

    /**
     * 分页查询库存工作单
     * @param wareOrderTaskDetailPage
     * @param wareOrderTaskDetailDto
     * @return
     */
    IPage<WareOrderTaskDetail> queryPage(Page<WareOrderTaskDetail> wareOrderTaskDetailPage, @Param("dto") WareOrderTaskDetailDto wareOrderTaskDetailDto);

    /**
     * 导出查询数量
     * @param wareOrderTaskDetailDto
     * @return
     */
    Integer queryCount(@Param("dto") WareOrderTaskDetailDto wareOrderTaskDetailDto);

    /**
     * 导出分页查询库存工作单
     * @param wareOrderTaskDetailPage
     * @param wareOrderTaskDetailDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> wareOrderTaskDetailPage, @Param("dto") WareOrderTaskDetailDto wareOrderTaskDetailDto);

    /**
     *
     * 查询所有库存工作单
     * @return
     */
    List<WareOrderTaskDetail> queryAll(@Param("dto") WareOrderTaskDetailDto wareOrderTaskDetailDto);

}
