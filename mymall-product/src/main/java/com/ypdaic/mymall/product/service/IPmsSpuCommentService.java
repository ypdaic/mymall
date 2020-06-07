package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.product.entity.PmsSpuComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.PmsSpuCommentDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 商品评价 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public interface IPmsSpuCommentService extends IService<PmsSpuComment> {

    /**
     * 新增商品评价
     * @param pmsSpuCommentDto
     * @return
     */
    PmsSpuComment add(PmsSpuCommentDto pmsSpuCommentDto);

    /**
     * 更新商品评价
     * @param pmsSpuCommentDto
     * @return
     */
    PmsSpuComment update(PmsSpuCommentDto pmsSpuCommentDto);

    /**
     * 删除商品评价
     * @param pmsSpuCommentDto
     * @return
     */
    PmsSpuComment delete(PmsSpuCommentDto pmsSpuCommentDto);

    /**
     * 分页查询商品评价
     * @param pmsSpuCommentDto
     * @param pmsSpuCommentPage
     * @return
     */
    IPage<PmsSpuComment> queryPage(PmsSpuCommentDto pmsSpuCommentDto, Page<PmsSpuComment> pmsSpuCommentPage);


    /**
     * 校验商品评价名称
     * @param pmsSpuCommentDto
     * @return
     */
    boolean checkName(PmsSpuCommentDto pmsSpuCommentDto, boolean isAdd);

    /**
     *
     * 查询所有商品评价
     * @return
     */
    List<PmsSpuComment> queryAll(PmsSpuCommentDto pmsSpuCommentDto);
}
