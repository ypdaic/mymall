package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.product.entity.PmsCommentReplay;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.PmsCommentReplayDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 商品评价回复关系 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public interface IPmsCommentReplayService extends IService<PmsCommentReplay> {

    /**
     * 新增商品评价回复关系
     * @param pmsCommentReplayDto
     * @return
     */
    PmsCommentReplay add(PmsCommentReplayDto pmsCommentReplayDto);

    /**
     * 更新商品评价回复关系
     * @param pmsCommentReplayDto
     * @return
     */
    PmsCommentReplay update(PmsCommentReplayDto pmsCommentReplayDto);

    /**
     * 删除商品评价回复关系
     * @param pmsCommentReplayDto
     * @return
     */
    PmsCommentReplay delete(PmsCommentReplayDto pmsCommentReplayDto);

    /**
     * 分页查询商品评价回复关系
     * @param pmsCommentReplayDto
     * @param pmsCommentReplayPage
     * @return
     */
    IPage<PmsCommentReplay> queryPage(PmsCommentReplayDto pmsCommentReplayDto, Page<PmsCommentReplay> pmsCommentReplayPage);


    /**
     * 校验商品评价回复关系名称
     * @param pmsCommentReplayDto
     * @return
     */
    boolean checkName(PmsCommentReplayDto pmsCommentReplayDto, boolean isAdd);

    /**
     *
     * 查询所有商品评价回复关系
     * @return
     */
    List<PmsCommentReplay> queryAll(PmsCommentReplayDto pmsCommentReplayDto);
}
