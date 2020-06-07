package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.CommentReplay;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.CommentReplayDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 商品评价回复关系 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface CommentReplayMapper extends BaseMapper<CommentReplay> {

    /**
     * 分页查询商品评价回复关系
     * @param commentReplayPage
     * @param commentReplayDto
     * @return
     */
    IPage<CommentReplay> queryPage(Page<CommentReplay> commentReplayPage, @Param("dto") CommentReplayDto commentReplayDto);

    /**
     * 导出查询数量
     * @param commentReplayDto
     * @return
     */
    Integer queryCount(@Param("dto") CommentReplayDto commentReplayDto);

    /**
     * 导出分页查询商品评价回复关系
     * @param commentReplayPage
     * @param commentReplayDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> commentReplayPage, @Param("dto") CommentReplayDto commentReplayDto);

    /**
     *
     * 查询所有商品评价回复关系
     * @return
     */
    List<CommentReplay> queryAll(@Param("dto") CommentReplayDto commentReplayDto);

}
