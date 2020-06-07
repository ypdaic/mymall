package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.PmsSpuComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.PmsSpuCommentDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 商品评价 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public interface PmsSpuCommentMapper extends BaseMapper<PmsSpuComment> {

    /**
     * 分页查询商品评价
     * @param pmsSpuCommentPage
     * @param pmsSpuCommentDto
     * @return
     */
    IPage<PmsSpuComment> queryPage(Page<PmsSpuComment> pmsSpuCommentPage, @Param("dto") PmsSpuCommentDto pmsSpuCommentDto);

    /**
     * 导出查询数量
     * @param pmsSpuCommentDto
     * @return
     */
    Integer queryCount(@Param("dto") PmsSpuCommentDto pmsSpuCommentDto);

    /**
     * 导出分页查询商品评价
     * @param pmsSpuCommentPage
     * @param pmsSpuCommentDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> pmsSpuCommentPage, @Param("dto") PmsSpuCommentDto pmsSpuCommentDto);

    /**
     *
     * 查询所有商品评价
     * @return
     */
    List<PmsSpuComment> queryAll(@Param("dto") PmsSpuCommentDto pmsSpuCommentDto);

}
