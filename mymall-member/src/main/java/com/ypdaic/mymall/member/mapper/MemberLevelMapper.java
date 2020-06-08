package com.ypdaic.mymall.member.mapper;

import com.ypdaic.mymall.member.entity.MemberLevel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.member.vo.MemberLevelDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 会员等级 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface MemberLevelMapper extends BaseMapper<MemberLevel> {

    /**
     * 分页查询会员等级
     * @param memberLevelPage
     * @param memberLevelDto
     * @return
     */
    IPage<MemberLevel> queryPage(Page<MemberLevel> memberLevelPage, @Param("dto") MemberLevelDto memberLevelDto);

    /**
     * 导出查询数量
     * @param memberLevelDto
     * @return
     */
    Integer queryCount(@Param("dto") MemberLevelDto memberLevelDto);

    /**
     * 导出分页查询会员等级
     * @param memberLevelPage
     * @param memberLevelDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> memberLevelPage, @Param("dto") MemberLevelDto memberLevelDto);

    /**
     *
     * 查询所有会员等级
     * @return
     */
    List<MemberLevel> queryAll(@Param("dto") MemberLevelDto memberLevelDto);

}
