package com.ypdaic.mymall.member.mapper;

import com.ypdaic.mymall.member.entity.MemberCollectSpu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.member.vo.MemberCollectSpuDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 会员收藏的商品 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface MemberCollectSpuMapper extends BaseMapper<MemberCollectSpu> {

    /**
     * 分页查询会员收藏的商品
     * @param memberCollectSpuPage
     * @param memberCollectSpuDto
     * @return
     */
    IPage<MemberCollectSpu> queryPage(Page<MemberCollectSpu> memberCollectSpuPage, @Param("dto") MemberCollectSpuDto memberCollectSpuDto);

    /**
     * 导出查询数量
     * @param memberCollectSpuDto
     * @return
     */
    Integer queryCount(@Param("dto") MemberCollectSpuDto memberCollectSpuDto);

    /**
     * 导出分页查询会员收藏的商品
     * @param memberCollectSpuPage
     * @param memberCollectSpuDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> memberCollectSpuPage, @Param("dto") MemberCollectSpuDto memberCollectSpuDto);

    /**
     *
     * 查询所有会员收藏的商品
     * @return
     */
    List<MemberCollectSpu> queryAll(@Param("dto") MemberCollectSpuDto memberCollectSpuDto);

}
