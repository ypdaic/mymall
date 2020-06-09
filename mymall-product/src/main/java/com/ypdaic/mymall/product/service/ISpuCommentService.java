package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.product.entity.SpuComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.SpuCommentDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 商品评价 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface ISpuCommentService extends IService<SpuComment> {

    /**
     * 新增商品评价
     * @param spuCommentDto
     * @return
     */
    SpuComment add(SpuCommentDto spuCommentDto);

    /**
     * 更新商品评价
     * @param spuCommentDto
     * @return
     */
    SpuComment update(SpuCommentDto spuCommentDto);

    /**
     * 删除商品评价
     * @param spuCommentDto
     * @return
     */
    SpuComment delete(SpuCommentDto spuCommentDto);

    /**
     * 分页查询商品评价
     * @param spuCommentDto
     * @param spuCommentPage
     * @return
     */
    IPage<SpuComment> queryPage(SpuCommentDto spuCommentDto, Page<SpuComment> spuCommentPage);


    /**
     * 校验商品评价名称
     * @param spuCommentDto
     * @return
     */
    boolean checkName(SpuCommentDto spuCommentDto, boolean isAdd);

    /**
     *
     * 查询所有商品评价
     * @return
     */
    List<SpuComment> queryAll(SpuCommentDto spuCommentDto);

    PageUtils queryPage(Map<String, Object> params);
}
