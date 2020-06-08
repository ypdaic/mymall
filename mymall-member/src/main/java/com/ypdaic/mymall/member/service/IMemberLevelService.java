package com.ypdaic.mymall.member.service;

import com.ypdaic.mymall.member.entity.MemberLevel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.member.vo.MemberLevelDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 会员等级 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IMemberLevelService extends IService<MemberLevel> {

    /**
     * 新增会员等级
     * @param memberLevelDto
     * @return
     */
    MemberLevel add(MemberLevelDto memberLevelDto);

    /**
     * 更新会员等级
     * @param memberLevelDto
     * @return
     */
    MemberLevel update(MemberLevelDto memberLevelDto);

    /**
     * 删除会员等级
     * @param memberLevelDto
     * @return
     */
    MemberLevel delete(MemberLevelDto memberLevelDto);

    /**
     * 分页查询会员等级
     * @param memberLevelDto
     * @param memberLevelPage
     * @return
     */
    IPage<MemberLevel> queryPage(MemberLevelDto memberLevelDto, Page<MemberLevel> memberLevelPage);


    /**
     * 校验会员等级名称
     * @param memberLevelDto
     * @return
     */
    boolean checkName(MemberLevelDto memberLevelDto, boolean isAdd);

    /**
     *
     * 查询所有会员等级
     * @return
     */
    List<MemberLevel> queryAll(MemberLevelDto memberLevelDto);
}
