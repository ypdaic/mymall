package com.ypdaic.mymall.member.mapper;

import com.ypdaic.mymall.member.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.member.vo.MemberDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 会员 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface MemberMapper extends BaseMapper<Member> {

    /**
     * 分页查询会员
     * @param memberPage
     * @param memberDto
     * @return
     */
    IPage<Member> queryPage(Page<Member> memberPage, @Param("dto") MemberDto memberDto);

    /**
     * 导出查询数量
     * @param memberDto
     * @return
     */
    Integer queryCount(@Param("dto") MemberDto memberDto);

    /**
     * 导出分页查询会员
     * @param memberPage
     * @param memberDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> memberPage, @Param("dto") MemberDto memberDto);

    /**
     *
     * 查询所有会员
     * @return
     */
    List<Member> queryAll(@Param("dto") MemberDto memberDto);

}
