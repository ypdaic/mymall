package com.ypdaic.mymall.ware.service.impl;

import com.ypdaic.mymall.ware.entity.WareOrderTaskDetail;
import com.ypdaic.mymall.ware.mapper.WareOrderTaskDetailMapper;
import com.ypdaic.mymall.ware.service.IWareOrderTaskDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.ware.vo.WareOrderTaskDetailDto;
import com.ypdaic.mymall.ware.enums.WareOrderTaskDetailExcelHeadersEnum;
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

/**
 * <p>
 * 库存工作单 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class WareOrderTaskDetailServiceImpl extends ServiceImpl<WareOrderTaskDetailMapper, WareOrderTaskDetail> implements IWareOrderTaskDetailService {


    /**
     * 新增库存工作单
     * @param wareOrderTaskDetailDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public WareOrderTaskDetail add(WareOrderTaskDetailDto wareOrderTaskDetailDto) {

        WareOrderTaskDetail wareOrderTaskDetail = new WareOrderTaskDetail();
        wareOrderTaskDetail.setSkuId(wareOrderTaskDetailDto.getSkuId());
        wareOrderTaskDetail.setSkuName(wareOrderTaskDetailDto.getSkuName());
        wareOrderTaskDetail.setSkuNum(wareOrderTaskDetailDto.getSkuNum());
        wareOrderTaskDetail.setTaskId(wareOrderTaskDetailDto.getTaskId());
        wareOrderTaskDetail.insert();
        return wareOrderTaskDetail;
    }

    /**
     * 更新库存工作单
     * @param wareOrderTaskDetailDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public WareOrderTaskDetail update(WareOrderTaskDetailDto wareOrderTaskDetailDto) {
        WareOrderTaskDetail wareOrderTaskDetail = baseMapper.selectById(wareOrderTaskDetailDto.getId());
        if (wareOrderTaskDetail == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(wareOrderTaskDetailDto.getSkuId(), wareOrderTaskDetail::setSkuId);
        JavaUtils.INSTANCE.acceptIfNotNull(wareOrderTaskDetailDto.getSkuName(), wareOrderTaskDetail::setSkuName);
        JavaUtils.INSTANCE.acceptIfNotNull(wareOrderTaskDetailDto.getSkuNum(), wareOrderTaskDetail::setSkuNum);
        JavaUtils.INSTANCE.acceptIfNotNull(wareOrderTaskDetailDto.getTaskId(), wareOrderTaskDetail::setTaskId);
        wareOrderTaskDetail.updateById();
        return wareOrderTaskDetail;
    }

    /**
     * 删除库存工作单
     * @param wareOrderTaskDetailDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public WareOrderTaskDetail delete(WareOrderTaskDetailDto wareOrderTaskDetailDto) {
        WareOrderTaskDetail wareOrderTaskDetail = baseMapper.selectById(wareOrderTaskDetailDto.getId());
        if (wareOrderTaskDetail == null) {
            return null;
        }
        wareOrderTaskDetail.deleteById();

        return wareOrderTaskDetail;
    }

    /**
     * 分页查询库存工作单
     * @param wareOrderTaskDetailDto
     * @param wareOrderTaskDetailPage
     * @return
     */
    @Override
    public IPage<WareOrderTaskDetail> queryPage(WareOrderTaskDetailDto wareOrderTaskDetailDto, Page<WareOrderTaskDetail> wareOrderTaskDetailPage) {

        return baseMapper.queryPage(wareOrderTaskDetailPage, wareOrderTaskDetailDto);
    }


    /**
     * 校验库存工作单名称
     * @param wareOrderTaskDetailDto
     * @return
     */
    @Override
    public boolean checkName(WareOrderTaskDetailDto wareOrderTaskDetailDto, boolean isAdd) {

        QueryWrapper<WareOrderTaskDetail> wareOrderTaskDetailQueryWrapper = new QueryWrapper<WareOrderTaskDetail>();
        wareOrderTaskDetailQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        wareOrderTaskDetailQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            wareOrderTaskDetailQueryWrapper.ne("id", wareOrderTaskDetailDto.getId());
        }

        Integer count = baseMapper.selectCount(wareOrderTaskDetailQueryWrapper);
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
    public List<WareOrderTaskDetail> queryAll(WareOrderTaskDetailDto wareOrderTaskDetailDto) {
        return baseMapper.queryAll(wareOrderTaskDetailDto);
    }

}
