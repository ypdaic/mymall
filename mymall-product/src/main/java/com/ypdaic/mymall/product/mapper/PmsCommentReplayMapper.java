package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.PmsCommentReplay;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.PmsCommentReplayDto;
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
 * @since 2020-06-07
 */
public interface PmsCommentReplayMapper extends BaseMapper<PmsCommentReplay> {

    /**
     * 分页查询商品评价回复关系
     * @param pmsCommentReplayPage
     * @param pmsCommentReplayDto
     * @return
     */
    IPage<PmsCommentReplay> queryPage(Page<PmsCommentReplay> pmsCommentReplayPage, @Param("dto") PmsCommentReplayDto pmsCommentReplayDto);

    /**
     * 导出查询数量
     * @param pmsCommentReplayDto
     * @return
     */
    Integer queryCount(@Param("dto") PmsCommentReplayDto pmsCommentReplayDto);

    /**
     * 导出分页查询商品评价回复关系
     * @param pmsCommentReplayPage
     * @param pmsCommentReplayDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> pmsCommentReplayPage, @Param("dto") PmsCommentReplayDto pmsCommentReplayDto);

    /**
     *
     * 查询所有商品评价回复关系
     * @return
     */
    List<PmsCommentReplay> queryAll(@Param("dto") PmsCommentReplayDto pmsCommentReplayDto);

}
