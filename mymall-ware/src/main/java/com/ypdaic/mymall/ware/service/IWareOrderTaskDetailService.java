package com.ypdaic.mymall.ware.service;

import com.ypdaic.mymall.ware.entity.WareOrderTaskDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.ware.vo.WareOrderTaskDetailDto;
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
public interface IWareOrderTaskDetailService extends IService<WareOrderTaskDetail> {

    /**
     * 新增库存工作单
     * @param wareOrderTaskDetailDto
     * @return
     */
    WareOrderTaskDetail add(WareOrderTaskDetailDto wareOrderTaskDetailDto);

    /**
     * 更新库存工作单
     * @param wareOrderTaskDetailDto
     * @return
     */
    WareOrderTaskDetail update(WareOrderTaskDetailDto wareOrderTaskDetailDto);

    /**
     * 删除库存工作单
     * @param wareOrderTaskDetailDto
     * @return
     */
    WareOrderTaskDetail delete(WareOrderTaskDetailDto wareOrderTaskDetailDto);

    /**
     * 分页查询库存工作单
     * @param wareOrderTaskDetailDto
     * @param wareOrderTaskDetailPage
     * @return
     */
    IPage<WareOrderTaskDetail> queryPage(WareOrderTaskDetailDto wareOrderTaskDetailDto, Page<WareOrderTaskDetail> wareOrderTaskDetailPage);


    /**
     * 校验库存工作单名称
     * @param wareOrderTaskDetailDto
     * @return
     */
    boolean checkName(WareOrderTaskDetailDto wareOrderTaskDetailDto, boolean isAdd);

    /**
     *
     * 查询所有库存工作单
     * @return
     */
    List<WareOrderTaskDetail> queryAll(WareOrderTaskDetailDto wareOrderTaskDetailDto);
}
