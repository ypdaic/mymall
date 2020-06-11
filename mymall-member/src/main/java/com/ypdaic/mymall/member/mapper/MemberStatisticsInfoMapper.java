package com.ypdaic.mymall.member.mapper;

import com.ypdaic.mymall.member.entity.MemberStatisticsInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.member.vo.MemberStatisticsInfoDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 会员统计信息 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-11
 */
public interface MemberStatisticsInfoMapper extends BaseMapper<MemberStatisticsInfo> {

    /**
     * 分页查询会员统计信息
     * @param memberStatisticsInfoPage
     * @param memberStatisticsInfoDto
     * @return
     */
    IPage<MemberStatisticsInfo> queryPage(Page<MemberStatisticsInfo> memberStatisticsInfoPage, @Param("dto") MemberStatisticsInfoDto memberStatisticsInfoDto);

    /**
     * 导出查询数量
     * @param memberStatisticsInfoDto
     * @return
     */
    Integer queryCount(@Param("dto") MemberStatisticsInfoDto memberStatisticsInfoDto);

    /**
     * 导出分页查询会员统计信息
     * @param memberStatisticsInfoPage
     * @param memberStatisticsInfoDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> memberStatisticsInfoPage, @Param("dto") MemberStatisticsInfoDto memberStatisticsInfoDto);

    /**
     *
     * 查询所有会员统计信息
     * @return
     */
    List<MemberStatisticsInfo> queryAll(@Param("dto") MemberStatisticsInfoDto memberStatisticsInfoDto);

}
