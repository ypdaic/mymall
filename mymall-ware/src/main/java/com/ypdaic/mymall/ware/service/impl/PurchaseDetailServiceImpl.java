package com.ypdaic.mymall.ware.service.impl;

import com.ypdaic.mymall.ware.entity.PurchaseDetail;
import com.ypdaic.mymall.ware.mapper.PurchaseDetailMapper;
import com.ypdaic.mymall.ware.service.IPurchaseDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.ware.vo.PurchaseDetailDto;
import com.ypdaic.mymall.ware.enums.PurchaseDetailExcelHeadersEnum;
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
 *  服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailMapper, PurchaseDetail> implements IPurchaseDetailService {


    /**
     * 新增
     * @param purchaseDetailDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PurchaseDetail add(PurchaseDetailDto purchaseDetailDto) {

        PurchaseDetail purchaseDetail = new PurchaseDetail();
        purchaseDetail.setPurchaseId(purchaseDetailDto.getPurchaseId());
        purchaseDetail.setSkuId(purchaseDetailDto.getSkuId());
        purchaseDetail.setSkuNum(purchaseDetailDto.getSkuNum());
        purchaseDetail.setSkuPrice(purchaseDetailDto.getSkuPrice());
        purchaseDetail.setWareId(purchaseDetailDto.getWareId());
        purchaseDetail.setStatus(purchaseDetailDto.getStatus());
        purchaseDetail.insert();
        return purchaseDetail;
    }

    /**
     * 更新
     * @param purchaseDetailDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PurchaseDetail update(PurchaseDetailDto purchaseDetailDto) {
        PurchaseDetail purchaseDetail = baseMapper.selectById(purchaseDetailDto.getId());
        if (purchaseDetail == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(purchaseDetailDto.getPurchaseId(), purchaseDetail::setPurchaseId);
        JavaUtils.INSTANCE.acceptIfNotNull(purchaseDetailDto.getSkuId(), purchaseDetail::setSkuId);
        JavaUtils.INSTANCE.acceptIfNotNull(purchaseDetailDto.getSkuNum(), purchaseDetail::setSkuNum);
        JavaUtils.INSTANCE.acceptIfNotNull(purchaseDetailDto.getSkuPrice(), purchaseDetail::setSkuPrice);
        JavaUtils.INSTANCE.acceptIfNotNull(purchaseDetailDto.getWareId(), purchaseDetail::setWareId);
        JavaUtils.INSTANCE.acceptIfNotNull(purchaseDetailDto.getStatus(), purchaseDetail::setStatus);
        purchaseDetail.updateById();
        return purchaseDetail;
    }

    /**
     * 删除
     * @param purchaseDetailDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PurchaseDetail delete(PurchaseDetailDto purchaseDetailDto) {
        PurchaseDetail purchaseDetail = baseMapper.selectById(purchaseDetailDto.getId());
        if (purchaseDetail == null) {
            return null;
        }
        purchaseDetail.deleteById();

        return purchaseDetail;
    }

    /**
     * 分页查询
     * @param purchaseDetailDto
     * @param purchaseDetailPage
     * @return
     */
    @Override
    public IPage<PurchaseDetail> queryPage(PurchaseDetailDto purchaseDetailDto, Page<PurchaseDetail> purchaseDetailPage) {

        return baseMapper.queryPage(purchaseDetailPage, purchaseDetailDto);
    }


    /**
     * 校验名称
     * @param purchaseDetailDto
     * @return
     */
    @Override
    public boolean checkName(PurchaseDetailDto purchaseDetailDto, boolean isAdd) {

        QueryWrapper<PurchaseDetail> purchaseDetailQueryWrapper = new QueryWrapper<PurchaseDetail>();
        purchaseDetailQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        purchaseDetailQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            purchaseDetailQueryWrapper.ne("id", purchaseDetailDto.getId());
        }

        Integer count = baseMapper.selectCount(purchaseDetailQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有
     * @return
     */
    public List<PurchaseDetail> queryAll(PurchaseDetailDto purchaseDetailDto) {
        return baseMapper.queryAll(purchaseDetailDto);
    }

}
