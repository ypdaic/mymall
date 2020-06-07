package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.product.entity.CommentReplay;
import com.ypdaic.mymall.product.mapper.CommentReplayMapper;
import com.ypdaic.mymall.product.service.ICommentReplayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.CommentReplayDto;
import com.ypdaic.mymall.product.enums.CommentReplayExcelHeadersEnum;
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
 * 商品评价回复关系 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class CommentReplayServiceImpl extends ServiceImpl<CommentReplayMapper, CommentReplay> implements ICommentReplayService {


    /**
     * 新增商品评价回复关系
     * @param commentReplayDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommentReplay add(CommentReplayDto commentReplayDto) {

        CommentReplay commentReplay = new CommentReplay();
        commentReplay.setCommentId(commentReplayDto.getCommentId());
        commentReplay.setReplyId(commentReplayDto.getReplyId());
        commentReplay.insert();
        return commentReplay;
    }

    /**
     * 更新商品评价回复关系
     * @param commentReplayDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommentReplay update(CommentReplayDto commentReplayDto) {
        CommentReplay commentReplay = baseMapper.selectById(commentReplayDto.getId());
        if (commentReplay == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(commentReplayDto.getCommentId(), commentReplay::setCommentId);
        JavaUtils.INSTANCE.acceptIfNotNull(commentReplayDto.getReplyId(), commentReplay::setReplyId);
        commentReplay.updateById();
        return commentReplay;
    }

    /**
     * 删除商品评价回复关系
     * @param commentReplayDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommentReplay delete(CommentReplayDto commentReplayDto) {
        CommentReplay commentReplay = baseMapper.selectById(commentReplayDto.getId());
        if (commentReplay == null) {
            return null;
        }
        commentReplay.deleteById();

        return commentReplay;
    }

    /**
     * 分页查询商品评价回复关系
     * @param commentReplayDto
     * @param commentReplayPage
     * @return
     */
    @Override
    public IPage<CommentReplay> queryPage(CommentReplayDto commentReplayDto, Page<CommentReplay> commentReplayPage) {

        return baseMapper.queryPage(commentReplayPage, commentReplayDto);
    }


    /**
     * 校验商品评价回复关系名称
     * @param commentReplayDto
     * @return
     */
    @Override
    public boolean checkName(CommentReplayDto commentReplayDto, boolean isAdd) {

        QueryWrapper<CommentReplay> commentReplayQueryWrapper = new QueryWrapper<CommentReplay>();
        commentReplayQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        commentReplayQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            commentReplayQueryWrapper.ne("id", commentReplayDto.getId());
        }

        Integer count = baseMapper.selectCount(commentReplayQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有商品评价回复关系
     * @return
     */
    public List<CommentReplay> queryAll(CommentReplayDto commentReplayDto) {
        return baseMapper.queryAll(commentReplayDto);
    }

}
