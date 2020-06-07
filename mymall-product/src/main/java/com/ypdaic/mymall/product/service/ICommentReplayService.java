package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.product.entity.CommentReplay;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.CommentReplayDto;
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
 * @since 2020-06-08
 */
public interface ICommentReplayService extends IService<CommentReplay> {

    /**
     * 新增商品评价回复关系
     * @param commentReplayDto
     * @return
     */
    CommentReplay add(CommentReplayDto commentReplayDto);

    /**
     * 更新商品评价回复关系
     * @param commentReplayDto
     * @return
     */
    CommentReplay update(CommentReplayDto commentReplayDto);

    /**
     * 删除商品评价回复关系
     * @param commentReplayDto
     * @return
     */
    CommentReplay delete(CommentReplayDto commentReplayDto);

    /**
     * 分页查询商品评价回复关系
     * @param commentReplayDto
     * @param commentReplayPage
     * @return
     */
    IPage<CommentReplay> queryPage(CommentReplayDto commentReplayDto, Page<CommentReplay> commentReplayPage);


    /**
     * 校验商品评价回复关系名称
     * @param commentReplayDto
     * @return
     */
    boolean checkName(CommentReplayDto commentReplayDto, boolean isAdd);

    /**
     *
     * 查询所有商品评价回复关系
     * @return
     */
    List<CommentReplay> queryAll(CommentReplayDto commentReplayDto);
}
