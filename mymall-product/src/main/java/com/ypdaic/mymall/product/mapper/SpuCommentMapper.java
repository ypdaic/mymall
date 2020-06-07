package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.SpuComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.SpuCommentDto;
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
 * @since 2020-06-08
 */
public interface SpuCommentMapper extends BaseMapper<SpuComment> {

    /**
     * 分页查询商品评价
     * @param spuCommentPage
     * @param spuCommentDto
     * @return
     */
    IPage<SpuComment> queryPage(Page<SpuComment> spuCommentPage, @Param("dto") SpuCommentDto spuCommentDto);

    /**
     * 导出查询数量
     * @param spuCommentDto
     * @return
     */
    Integer queryCount(@Param("dto") SpuCommentDto spuCommentDto);

    /**
     * 导出分页查询商品评价
     * @param spuCommentPage
     * @param spuCommentDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> spuCommentPage, @Param("dto") SpuCommentDto spuCommentDto);

    /**
     *
     * 查询所有商品评价
     * @return
     */
    List<SpuComment> queryAll(@Param("dto") SpuCommentDto spuCommentDto);

}
