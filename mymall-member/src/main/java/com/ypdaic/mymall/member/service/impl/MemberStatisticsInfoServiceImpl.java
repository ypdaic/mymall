package com.ypdaic.mymall.member.service.impl;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.member.entity.MemberStatisticsInfo;
import com.ypdaic.mymall.member.mapper.MemberStatisticsInfoMapper;
import com.ypdaic.mymall.member.service.IMemberStatisticsInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.member.vo.MemberStatisticsInfoDto;
import com.ypdaic.mymall.member.enums.MemberStatisticsInfoExcelHeadersEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ypdaic.mymall.common.util.ExcelUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.ypdaic.mymall.common.enums.EnableEnum;
import com.ypdaic.mymall.common.util.JavaUtils;

/**
 * <p>
 * 会员统计信息 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-11
 */
@Service
public class MemberStatisticsInfoServiceImpl extends ServiceImpl<MemberStatisticsInfoMapper, MemberStatisticsInfo> implements IMemberStatisticsInfoService {


    /**
     * 新增会员统计信息
     * @param memberStatisticsInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MemberStatisticsInfo add(MemberStatisticsInfoDto memberStatisticsInfoDto) {

        MemberStatisticsInfo memberStatisticsInfo = new MemberStatisticsInfo();
        memberStatisticsInfo.setMemberId(memberStatisticsInfoDto.getMemberId());
        memberStatisticsInfo.setConsumeAmount(memberStatisticsInfoDto.getConsumeAmount());
        memberStatisticsInfo.setCouponAmount(memberStatisticsInfoDto.getCouponAmount());
        memberStatisticsInfo.setOrderCount(memberStatisticsInfoDto.getOrderCount());
        memberStatisticsInfo.setCouponCount(memberStatisticsInfoDto.getCouponCount());
        memberStatisticsInfo.setCommentCount(memberStatisticsInfoDto.getCommentCount());
        memberStatisticsInfo.setReturnOrderCount(memberStatisticsInfoDto.getReturnOrderCount());
        memberStatisticsInfo.setLoginCount(memberStatisticsInfoDto.getLoginCount());
        memberStatisticsInfo.setAttendCount(memberStatisticsInfoDto.getAttendCount());
        memberStatisticsInfo.setFansCount(memberStatisticsInfoDto.getFansCount());
        memberStatisticsInfo.setCollectProductCount(memberStatisticsInfoDto.getCollectProductCount());
        memberStatisticsInfo.setCollectSubjectCount(memberStatisticsInfoDto.getCollectSubjectCount());
        memberStatisticsInfo.setCollectCommentCount(memberStatisticsInfoDto.getCollectCommentCount());
        memberStatisticsInfo.setInviteFriendCount(memberStatisticsInfoDto.getInviteFriendCount());
        memberStatisticsInfo.insert();
        return memberStatisticsInfo;
    }

    /**
     * 更新会员统计信息
     * @param memberStatisticsInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MemberStatisticsInfo update(MemberStatisticsInfoDto memberStatisticsInfoDto) {
        MemberStatisticsInfo memberStatisticsInfo = baseMapper.selectById(memberStatisticsInfoDto.getId());
        if (memberStatisticsInfo == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(memberStatisticsInfoDto.getMemberId(), memberStatisticsInfo::setMemberId);
        JavaUtils.INSTANCE.acceptIfNotNull(memberStatisticsInfoDto.getConsumeAmount(), memberStatisticsInfo::setConsumeAmount);
        JavaUtils.INSTANCE.acceptIfNotNull(memberStatisticsInfoDto.getCouponAmount(), memberStatisticsInfo::setCouponAmount);
        JavaUtils.INSTANCE.acceptIfNotNull(memberStatisticsInfoDto.getOrderCount(), memberStatisticsInfo::setOrderCount);
        JavaUtils.INSTANCE.acceptIfNotNull(memberStatisticsInfoDto.getCouponCount(), memberStatisticsInfo::setCouponCount);
        JavaUtils.INSTANCE.acceptIfNotNull(memberStatisticsInfoDto.getCommentCount(), memberStatisticsInfo::setCommentCount);
        JavaUtils.INSTANCE.acceptIfNotNull(memberStatisticsInfoDto.getReturnOrderCount(), memberStatisticsInfo::setReturnOrderCount);
        JavaUtils.INSTANCE.acceptIfNotNull(memberStatisticsInfoDto.getLoginCount(), memberStatisticsInfo::setLoginCount);
        JavaUtils.INSTANCE.acceptIfNotNull(memberStatisticsInfoDto.getAttendCount(), memberStatisticsInfo::setAttendCount);
        JavaUtils.INSTANCE.acceptIfNotNull(memberStatisticsInfoDto.getFansCount(), memberStatisticsInfo::setFansCount);
        JavaUtils.INSTANCE.acceptIfNotNull(memberStatisticsInfoDto.getCollectProductCount(), memberStatisticsInfo::setCollectProductCount);
        JavaUtils.INSTANCE.acceptIfNotNull(memberStatisticsInfoDto.getCollectSubjectCount(), memberStatisticsInfo::setCollectSubjectCount);
        JavaUtils.INSTANCE.acceptIfNotNull(memberStatisticsInfoDto.getCollectCommentCount(), memberStatisticsInfo::setCollectCommentCount);
        JavaUtils.INSTANCE.acceptIfNotNull(memberStatisticsInfoDto.getInviteFriendCount(), memberStatisticsInfo::setInviteFriendCount);
        memberStatisticsInfo.updateById();
        return memberStatisticsInfo;
    }

    /**
     * 删除会员统计信息
     * @param memberStatisticsInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MemberStatisticsInfo delete(MemberStatisticsInfoDto memberStatisticsInfoDto) {
        MemberStatisticsInfo memberStatisticsInfo = baseMapper.selectById(memberStatisticsInfoDto.getId());
        if (memberStatisticsInfo == null) {
            return null;
        }
        memberStatisticsInfo.deleteById();

        return memberStatisticsInfo;
    }

    /**
     * 分页查询会员统计信息
     * @param memberStatisticsInfoDto
     * @param memberStatisticsInfoPage
     * @return
     */
    @Override
    public IPage<MemberStatisticsInfo> queryPage(MemberStatisticsInfoDto memberStatisticsInfoDto, Page<MemberStatisticsInfo> memberStatisticsInfoPage) {

        return baseMapper.queryPage(memberStatisticsInfoPage, memberStatisticsInfoDto);
    }


    /**
     * 校验会员统计信息名称
     * @param memberStatisticsInfoDto
     * @return
     */
    @Override
    public boolean checkName(MemberStatisticsInfoDto memberStatisticsInfoDto, boolean isAdd) {

        QueryWrapper<MemberStatisticsInfo> memberStatisticsInfoQueryWrapper = new QueryWrapper<MemberStatisticsInfo>();
        memberStatisticsInfoQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        memberStatisticsInfoQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            memberStatisticsInfoQueryWrapper.ne("id", memberStatisticsInfoDto.getId());
        }

        Integer count = baseMapper.selectCount(memberStatisticsInfoQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有会员统计信息
     * @return
     */
    @Override
    public List<MemberStatisticsInfo> queryAll(MemberStatisticsInfoDto memberStatisticsInfoDto) {
        return baseMapper.queryAll(memberStatisticsInfoDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberStatisticsInfo> page = this.page(
                new Query<MemberStatisticsInfo>().getPage(params),
                new QueryWrapper<MemberStatisticsInfo>()
        );

        return new PageUtils(page);
    }

}
