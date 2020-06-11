package com.ypdaic.mymall.member.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.member.entity.MemberLoginLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.member.vo.MemberLoginLogDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 会员登录记录 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IMemberLoginLogService extends IService<MemberLoginLog> {

    /**
     * 新增会员登录记录
     * @param memberLoginLogDto
     * @return
     */
    MemberLoginLog add(MemberLoginLogDto memberLoginLogDto);

    /**
     * 更新会员登录记录
     * @param memberLoginLogDto
     * @return
     */
    MemberLoginLog update(MemberLoginLogDto memberLoginLogDto);

    /**
     * 删除会员登录记录
     * @param memberLoginLogDto
     * @return
     */
    MemberLoginLog delete(MemberLoginLogDto memberLoginLogDto);

    /**
     * 分页查询会员登录记录
     * @param memberLoginLogDto
     * @param memberLoginLogPage
     * @return
     */
    IPage<MemberLoginLog> queryPage(MemberLoginLogDto memberLoginLogDto, Page<MemberLoginLog> memberLoginLogPage);


    /**
     * 校验会员登录记录名称
     * @param memberLoginLogDto
     * @return
     */
    boolean checkName(MemberLoginLogDto memberLoginLogDto, boolean isAdd);

    /**
     *
     * 查询所有会员登录记录
     * @return
     */
    List<MemberLoginLog> queryAll(MemberLoginLogDto memberLoginLogDto);

    PageUtils queryPage(Map<String, Object> params);

}
