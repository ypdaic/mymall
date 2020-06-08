package com.ypdaic.mymall.member.service.impl;

import com.ypdaic.mymall.member.entity.Member;
import com.ypdaic.mymall.member.mapper.MemberMapper;
import com.ypdaic.mymall.member.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.member.vo.MemberDto;
import com.ypdaic.mymall.member.enums.MemberExcelHeadersEnum;
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
import java.util.Date;
import java.util.Date;

/**
 * <p>
 * 会员 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {


    /**
     * 新增会员
     * @param memberDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Member add(MemberDto memberDto) {

        Member member = new Member();
        member.setLevelId(memberDto.getLevelId());
        member.setUsername(memberDto.getUsername());
        member.setPassword(memberDto.getPassword());
        member.setNickname(memberDto.getNickname());
        member.setMobile(memberDto.getMobile());
        member.setEmail(memberDto.getEmail());
        member.setHeader(memberDto.getHeader());
        member.setGender(memberDto.getGender());
        Date date8= new Date();
        member.setBirth(date8);
        member.setCity(memberDto.getCity());
        member.setJob(memberDto.getJob());
        member.setSign(memberDto.getSign());
        member.setSourceType(memberDto.getSourceType());
        member.setIntegration(memberDto.getIntegration());
        member.setGrowth(memberDto.getGrowth());
        member.setStatus(memberDto.getStatus());
        Date date16= new Date();
        member.setCreateTime(date16);
        member.insert();
        return member;
    }

    /**
     * 更新会员
     * @param memberDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Member update(MemberDto memberDto) {
        Member member = baseMapper.selectById(memberDto.getId());
        if (member == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(memberDto.getLevelId(), member::setLevelId);
        JavaUtils.INSTANCE.acceptIfNotNull(memberDto.getUsername(), member::setUsername);
        JavaUtils.INSTANCE.acceptIfNotNull(memberDto.getPassword(), member::setPassword);
        JavaUtils.INSTANCE.acceptIfNotNull(memberDto.getNickname(), member::setNickname);
        JavaUtils.INSTANCE.acceptIfNotNull(memberDto.getMobile(), member::setMobile);
        JavaUtils.INSTANCE.acceptIfNotNull(memberDto.getEmail(), member::setEmail);
        JavaUtils.INSTANCE.acceptIfNotNull(memberDto.getHeader(), member::setHeader);
        JavaUtils.INSTANCE.acceptIfNotNull(memberDto.getGender(), member::setGender);
        JavaUtils.INSTANCE.acceptIfNotNull(memberDto.getBirth(), member::setBirth);
        JavaUtils.INSTANCE.acceptIfNotNull(memberDto.getCity(), member::setCity);
        JavaUtils.INSTANCE.acceptIfNotNull(memberDto.getJob(), member::setJob);
        JavaUtils.INSTANCE.acceptIfNotNull(memberDto.getSign(), member::setSign);
        JavaUtils.INSTANCE.acceptIfNotNull(memberDto.getSourceType(), member::setSourceType);
        JavaUtils.INSTANCE.acceptIfNotNull(memberDto.getIntegration(), member::setIntegration);
        JavaUtils.INSTANCE.acceptIfNotNull(memberDto.getGrowth(), member::setGrowth);
        JavaUtils.INSTANCE.acceptIfNotNull(memberDto.getStatus(), member::setStatus);
        member.updateById();
        return member;
    }

    /**
     * 删除会员
     * @param memberDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Member delete(MemberDto memberDto) {
        Member member = baseMapper.selectById(memberDto.getId());
        if (member == null) {
            return null;
        }
        member.deleteById();

        return member;
    }

    /**
     * 分页查询会员
     * @param memberDto
     * @param memberPage
     * @return
     */
    @Override
    public IPage<Member> queryPage(MemberDto memberDto, Page<Member> memberPage) {

        return baseMapper.queryPage(memberPage, memberDto);
    }


    /**
     * 校验会员名称
     * @param memberDto
     * @return
     */
    @Override
    public boolean checkName(MemberDto memberDto, boolean isAdd) {

        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<Member>();
        memberQueryWrapper.eq("username", memberDto.getUsername());
        memberQueryWrapper.eq("nickname", memberDto.getNickname());
        memberQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        memberQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            memberQueryWrapper.ne("id", memberDto.getId());
        }

        Integer count = baseMapper.selectCount(memberQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有会员
     * @return
     */
    public List<Member> queryAll(MemberDto memberDto) {
        return baseMapper.queryAll(memberDto);
    }

}
