package com.ypdaic.mymall.ware.service;

import com.ypdaic.mymall.ware.entity.WareOrderTask;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.ware.vo.WareOrderTaskDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 库存工作单 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IWareOrderTaskService extends IService<WareOrderTask> {

    /**
     * 新增库存工作单
     * @param wareOrderTaskDto
     * @return
     */
    WareOrderTask add(WareOrderTaskDto wareOrderTaskDto);

    /**
     * 更新库存工作单
     * @param wareOrderTaskDto
     * @return
     */
    WareOrderTask update(WareOrderTaskDto wareOrderTaskDto);

    /**
     * 删除库存工作单
     * @param wareOrderTaskDto
     * @return
     */
    WareOrderTask delete(WareOrderTaskDto wareOrderTaskDto);

    /**
     * 分页查询库存工作单
     * @param wareOrderTaskDto
     * @param wareOrderTaskPage
     * @return
     */
    IPage<WareOrderTask> queryPage(WareOrderTaskDto wareOrderTaskDto, Page<WareOrderTask> wareOrderTaskPage);


    /**
     * 校验库存工作单名称
     * @param wareOrderTaskDto
     * @return
     */
    boolean checkName(WareOrderTaskDto wareOrderTaskDto, boolean isAdd);

    /**
     *
     * 查询所有库存工作单
     * @return
     */
    List<WareOrderTask> queryAll(WareOrderTaskDto wareOrderTaskDto);
}
