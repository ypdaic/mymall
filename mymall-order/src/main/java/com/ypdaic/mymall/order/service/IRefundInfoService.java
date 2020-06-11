package com.ypdaic.mymall.order.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.order.entity.RefundInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.order.vo.RefundInfoDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 退款信息 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IRefundInfoService extends IService<RefundInfo> {

    /**
     * 新增退款信息
     * @param refundInfoDto
     * @return
     */
    RefundInfo add(RefundInfoDto refundInfoDto);

    /**
     * 更新退款信息
     * @param refundInfoDto
     * @return
     */
    RefundInfo update(RefundInfoDto refundInfoDto);

    /**
     * 删除退款信息
     * @param refundInfoDto
     * @return
     */
    RefundInfo delete(RefundInfoDto refundInfoDto);

    /**
     * 分页查询退款信息
     * @param refundInfoDto
     * @param refundInfoPage
     * @return
     */
    IPage<RefundInfo> queryPage(RefundInfoDto refundInfoDto, Page<RefundInfo> refundInfoPage);


    /**
     * 校验退款信息名称
     * @param refundInfoDto
     * @return
     */
    boolean checkName(RefundInfoDto refundInfoDto, boolean isAdd);

    /**
     *
     * 查询所有退款信息
     * @return
     */
    List<RefundInfo> queryAll(RefundInfoDto refundInfoDto);

    PageUtils queryPage(Map<String, Object> params);
}
