package com.ypdaic.mymall.member.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.member.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.member.vo.MemberDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 会员 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IMemberService extends IService<Member> {

    /**
     * 新增会员
     * @param memberDto
     * @return
     */
    Member add(MemberDto memberDto);

    /**
     * 更新会员
     * @param memberDto
     * @return
     */
    Member update(MemberDto memberDto);

    /**
     * 删除会员
     * @param memberDto
     * @return
     */
    Member delete(MemberDto memberDto);

    /**
     * 分页查询会员
     * @param memberDto
     * @param memberPage
     * @return
     */
    IPage<Member> queryPage(MemberDto memberDto, Page<Member> memberPage);


    /**
     * 校验会员名称
     * @param memberDto
     * @return
     */
    boolean checkName(MemberDto memberDto, boolean isAdd);

    /**
     *
     * 查询所有会员
     * @return
     */
    List<Member> queryAll(MemberDto memberDto);

    PageUtils queryPage(Map<String, Object> params);


}
