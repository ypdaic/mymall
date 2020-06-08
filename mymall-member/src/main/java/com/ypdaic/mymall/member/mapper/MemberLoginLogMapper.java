package com.ypdaic.mymall.member.mapper;

import com.ypdaic.mymall.member.entity.MemberLoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.member.vo.MemberLoginLogDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 会员登录记录 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface MemberLoginLogMapper extends BaseMapper<MemberLoginLog> {

    /**
     * 分页查询会员登录记录
     * @param memberLoginLogPage
     * @param memberLoginLogDto
     * @return
     */
    IPage<MemberLoginLog> queryPage(Page<MemberLoginLog> memberLoginLogPage, @Param("dto") MemberLoginLogDto memberLoginLogDto);

    /**
     * 导出查询数量
     * @param memberLoginLogDto
     * @return
     */
    Integer queryCount(@Param("dto") MemberLoginLogDto memberLoginLogDto);

    /**
     * 导出分页查询会员登录记录
     * @param memberLoginLogPage
     * @param memberLoginLogDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> memberLoginLogPage, @Param("dto") MemberLoginLogDto memberLoginLogDto);

    /**
     *
     * 查询所有会员登录记录
     * @return
     */
    List<MemberLoginLog> queryAll(@Param("dto") MemberLoginLogDto memberLoginLogDto);

}
