package com.ypdaic.mymall.member.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.member.entity.MemberStatisticsInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.member.vo.MemberStatisticsInfoDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 会员统计信息 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-11
 */
public interface IMemberStatisticsInfoService extends IService<MemberStatisticsInfo> {

    /**
     * 新增会员统计信息
     * @param memberStatisticsInfoDto
     * @return
     */
    MemberStatisticsInfo add(MemberStatisticsInfoDto memberStatisticsInfoDto);

    /**
     * 更新会员统计信息
     * @param memberStatisticsInfoDto
     * @return
     */
    MemberStatisticsInfo update(MemberStatisticsInfoDto memberStatisticsInfoDto);

    /**
     * 删除会员统计信息
     * @param memberStatisticsInfoDto
     * @return
     */
    MemberStatisticsInfo delete(MemberStatisticsInfoDto memberStatisticsInfoDto);

    /**
     * 分页查询会员统计信息
     * @param memberStatisticsInfoDto
     * @param memberStatisticsInfoPage
     * @return
     */
    IPage<MemberStatisticsInfo> queryPage(MemberStatisticsInfoDto memberStatisticsInfoDto, Page<MemberStatisticsInfo> memberStatisticsInfoPage);


    /**
     * 校验会员统计信息名称
     * @param memberStatisticsInfoDto
     * @return
     */
    boolean checkName(MemberStatisticsInfoDto memberStatisticsInfoDto, boolean isAdd);

    /**
     *
     * 查询所有会员统计信息
     * @return
     */
    List<MemberStatisticsInfo> queryAll(MemberStatisticsInfoDto memberStatisticsInfoDto);

    PageUtils queryPage(Map<String, Object> params);
}
