package com.ypdaic.mymall.ware.mapper;

import com.ypdaic.mymall.ware.entity.WareOrderTask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.ware.vo.WareOrderTaskDto;
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
public interface WareOrderTaskMapper extends BaseMapper<WareOrderTask> {

    /**
     * 分页查询库存工作单
     * @param wareOrderTaskPage
     * @param wareOrderTaskDto
     * @return
     */
    IPage<WareOrderTask> queryPage(Page<WareOrderTask> wareOrderTaskPage, @Param("dto") WareOrderTaskDto wareOrderTaskDto);

    /**
     * 导出查询数量
     * @param wareOrderTaskDto
     * @return
     */
    Integer queryCount(@Param("dto") WareOrderTaskDto wareOrderTaskDto);

    /**
     * 导出分页查询库存工作单
     * @param wareOrderTaskPage
     * @param wareOrderTaskDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> wareOrderTaskPage, @Param("dto") WareOrderTaskDto wareOrderTaskDto);

    /**
     *
     * 查询所有库存工作单
     * @return
     */
    List<WareOrderTask> queryAll(@Param("dto") WareOrderTaskDto wareOrderTaskDto);

}
