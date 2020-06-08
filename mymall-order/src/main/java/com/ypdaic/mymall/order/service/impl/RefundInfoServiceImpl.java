package com.ypdaic.mymall.order.service.impl;

import com.ypdaic.mymall.order.entity.RefundInfo;
import com.ypdaic.mymall.order.mapper.RefundInfoMapper;
import com.ypdaic.mymall.order.service.IRefundInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.order.vo.RefundInfoDto;
import com.ypdaic.mymall.order.enums.RefundInfoExcelHeadersEnum;
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
 * 退款信息 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class RefundInfoServiceImpl extends ServiceImpl<RefundInfoMapper, RefundInfo> implements IRefundInfoService {


    /**
     * 新增退款信息
     * @param refundInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public RefundInfo add(RefundInfoDto refundInfoDto) {

        RefundInfo refundInfo = new RefundInfo();
        refundInfo.setOrderReturnId(refundInfoDto.getOrderReturnId());
        refundInfo.setRefund(refundInfoDto.getRefund());
        refundInfo.setRefundSn(refundInfoDto.getRefundSn());
        refundInfo.setRefundStatus(refundInfoDto.getRefundStatus());
        refundInfo.setRefundChannel(refundInfoDto.getRefundChannel());
        refundInfo.setRefundContent(refundInfoDto.getRefundContent());
        refundInfo.insert();
        return refundInfo;
    }

    /**
     * 更新退款信息
     * @param refundInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public RefundInfo update(RefundInfoDto refundInfoDto) {
        RefundInfo refundInfo = baseMapper.selectById(refundInfoDto.getId());
        if (refundInfo == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(refundInfoDto.getOrderReturnId(), refundInfo::setOrderReturnId);
        JavaUtils.INSTANCE.acceptIfNotNull(refundInfoDto.getRefund(), refundInfo::setRefund);
        JavaUtils.INSTANCE.acceptIfNotNull(refundInfoDto.getRefundSn(), refundInfo::setRefundSn);
        JavaUtils.INSTANCE.acceptIfNotNull(refundInfoDto.getRefundStatus(), refundInfo::setRefundStatus);
        JavaUtils.INSTANCE.acceptIfNotNull(refundInfoDto.getRefundChannel(), refundInfo::setRefundChannel);
        JavaUtils.INSTANCE.acceptIfNotNull(refundInfoDto.getRefundContent(), refundInfo::setRefundContent);
        refundInfo.updateById();
        return refundInfo;
    }

    /**
     * 删除退款信息
     * @param refundInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public RefundInfo delete(RefundInfoDto refundInfoDto) {
        RefundInfo refundInfo = baseMapper.selectById(refundInfoDto.getId());
        if (refundInfo == null) {
            return null;
        }
        refundInfo.deleteById();

        return refundInfo;
    }

    /**
     * 分页查询退款信息
     * @param refundInfoDto
     * @param refundInfoPage
     * @return
     */
    @Override
    public IPage<RefundInfo> queryPage(RefundInfoDto refundInfoDto, Page<RefundInfo> refundInfoPage) {

        return baseMapper.queryPage(refundInfoPage, refundInfoDto);
    }


    /**
     * 校验退款信息名称
     * @param refundInfoDto
     * @return
     */
    @Override
    public boolean checkName(RefundInfoDto refundInfoDto, boolean isAdd) {

        QueryWrapper<RefundInfo> refundInfoQueryWrapper = new QueryWrapper<RefundInfo>();
        refundInfoQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        refundInfoQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            refundInfoQueryWrapper.ne("id", refundInfoDto.getId());
        }

        Integer count = baseMapper.selectCount(refundInfoQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有退款信息
     * @return
     */
    public List<RefundInfo> queryAll(RefundInfoDto refundInfoDto) {
        return baseMapper.queryAll(refundInfoDto);
    }

}
