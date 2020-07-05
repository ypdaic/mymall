package com.ypdaic.mymall.ware.service.impl;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.ware.entity.WareOrderTask;
import com.ypdaic.mymall.ware.entity.WareOrderTaskDetail;
import com.ypdaic.mymall.ware.mapper.WareOrderTaskMapper;
import com.ypdaic.mymall.ware.service.IWareOrderTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.ware.vo.WareOrderTaskDto;
import com.ypdaic.mymall.ware.enums.WareOrderTaskExcelHeadersEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ypdaic.mymall.common.util.ExcelUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.ypdaic.mymall.common.enums.EnableEnum;
import com.ypdaic.mymall.common.util.JavaUtils;
import java.util.Date;

/**
 * <p>
 * 库存工作单 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class WareOrderTaskServiceImpl extends ServiceImpl<WareOrderTaskMapper, WareOrderTask> implements IWareOrderTaskService {


    /**
     * 新增库存工作单
     * @param wareOrderTaskDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public WareOrderTask add(WareOrderTaskDto wareOrderTaskDto) {

        WareOrderTask wareOrderTask = new WareOrderTask();
        wareOrderTask.setOrderId(wareOrderTaskDto.getOrderId());
        wareOrderTask.setOrderSn(wareOrderTaskDto.getOrderSn());
        wareOrderTask.setConsignee(wareOrderTaskDto.getConsignee());
        wareOrderTask.setConsigneeTel(wareOrderTaskDto.getConsigneeTel());
        wareOrderTask.setDeliveryAddress(wareOrderTaskDto.getDeliveryAddress());
        wareOrderTask.setOrderComment(wareOrderTaskDto.getOrderComment());
        wareOrderTask.setPaymentWay(wareOrderTaskDto.getPaymentWay());
        wareOrderTask.setTaskStatus(wareOrderTaskDto.getTaskStatus());
        wareOrderTask.setOrderBody(wareOrderTaskDto.getOrderBody());
        wareOrderTask.setTrackingNo(wareOrderTaskDto.getTrackingNo());
        Date date10= new Date();
        wareOrderTask.setCreateTime(date10);
        wareOrderTask.setWareId(wareOrderTaskDto.getWareId());
        wareOrderTask.setTaskComment(wareOrderTaskDto.getTaskComment());
        wareOrderTask.insert();
        return wareOrderTask;
    }

    /**
     * 更新库存工作单
     * @param wareOrderTaskDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public WareOrderTask update(WareOrderTaskDto wareOrderTaskDto) {
        WareOrderTask wareOrderTask = baseMapper.selectById(wareOrderTaskDto.getId());
        if (wareOrderTask == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(wareOrderTaskDto.getOrderId(), wareOrderTask::setOrderId);
        JavaUtils.INSTANCE.acceptIfNotNull(wareOrderTaskDto.getOrderSn(), wareOrderTask::setOrderSn);
        JavaUtils.INSTANCE.acceptIfNotNull(wareOrderTaskDto.getConsignee(), wareOrderTask::setConsignee);
        JavaUtils.INSTANCE.acceptIfNotNull(wareOrderTaskDto.getConsigneeTel(), wareOrderTask::setConsigneeTel);
        JavaUtils.INSTANCE.acceptIfNotNull(wareOrderTaskDto.getDeliveryAddress(), wareOrderTask::setDeliveryAddress);
        JavaUtils.INSTANCE.acceptIfNotNull(wareOrderTaskDto.getOrderComment(), wareOrderTask::setOrderComment);
        JavaUtils.INSTANCE.acceptIfNotNull(wareOrderTaskDto.getPaymentWay(), wareOrderTask::setPaymentWay);
        JavaUtils.INSTANCE.acceptIfNotNull(wareOrderTaskDto.getTaskStatus(), wareOrderTask::setTaskStatus);
        JavaUtils.INSTANCE.acceptIfNotNull(wareOrderTaskDto.getOrderBody(), wareOrderTask::setOrderBody);
        JavaUtils.INSTANCE.acceptIfNotNull(wareOrderTaskDto.getTrackingNo(), wareOrderTask::setTrackingNo);
        JavaUtils.INSTANCE.acceptIfNotNull(wareOrderTaskDto.getWareId(), wareOrderTask::setWareId);
        JavaUtils.INSTANCE.acceptIfNotNull(wareOrderTaskDto.getTaskComment(), wareOrderTask::setTaskComment);
        wareOrderTask.updateById();
        return wareOrderTask;
    }

    /**
     * 删除库存工作单
     * @param wareOrderTaskDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public WareOrderTask delete(WareOrderTaskDto wareOrderTaskDto) {
        WareOrderTask wareOrderTask = baseMapper.selectById(wareOrderTaskDto.getId());
        if (wareOrderTask == null) {
            return null;
        }
        wareOrderTask.deleteById();

        return wareOrderTask;
    }

    /**
     * 分页查询库存工作单
     * @param wareOrderTaskDto
     * @param wareOrderTaskPage
     * @return
     */
    @Override
    public IPage<WareOrderTask> queryPage(WareOrderTaskDto wareOrderTaskDto, Page<WareOrderTask> wareOrderTaskPage) {

        return baseMapper.queryPage(wareOrderTaskPage, wareOrderTaskDto);
    }


    /**
     * 校验库存工作单名称
     * @param wareOrderTaskDto
     * @return
     */
    @Override
    public boolean checkName(WareOrderTaskDto wareOrderTaskDto, boolean isAdd) {

        QueryWrapper<WareOrderTask> wareOrderTaskQueryWrapper = new QueryWrapper<WareOrderTask>();
        wareOrderTaskQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        wareOrderTaskQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            wareOrderTaskQueryWrapper.ne("id", wareOrderTaskDto.getId());
        }

        Integer count = baseMapper.selectCount(wareOrderTaskQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有库存工作单
     * @return
     */
    public List<WareOrderTask> queryAll(WareOrderTaskDto wareOrderTaskDto) {
        return baseMapper.queryAll(wareOrderTaskDto);
    }


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WareOrderTask> page = this.page(
                new Query<WareOrderTask>().getPage(params),
                new QueryWrapper<WareOrderTask>()
        );

        return new PageUtils(page);
    }

    @Override
    public WareOrderTask getOrderTaskByOrderSn(String orderSn) {

        QueryWrapper<WareOrderTask> wareOrderTaskQueryWrapper = new QueryWrapper<>();
        wareOrderTaskQueryWrapper.eq("order_sn", orderSn);
        return baseMapper.selectOne(wareOrderTaskQueryWrapper);

    }

}
