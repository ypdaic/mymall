package com.ypdaic.mymall.member.service.impl;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.member.entity.MemberLevel;
import com.ypdaic.mymall.member.mapper.MemberLevelMapper;
import com.ypdaic.mymall.member.service.IMemberLevelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.member.vo.MemberLevelDto;
import com.ypdaic.mymall.member.enums.MemberLevelExcelHeadersEnum;
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
 * 会员等级 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelMapper, MemberLevel> implements IMemberLevelService {


    /**
     * 新增会员等级
     * @param memberLevelDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MemberLevel add(MemberLevelDto memberLevelDto) {

        MemberLevel memberLevel = new MemberLevel();
        memberLevel.setName(memberLevelDto.getName());
        memberLevel.setGrowthPoint(memberLevelDto.getGrowthPoint());
        memberLevel.setDefaultStatus(memberLevelDto.getDefaultStatus());
        memberLevel.setFreeFreightPoint(memberLevelDto.getFreeFreightPoint());
        memberLevel.setCommentGrowthPoint(memberLevelDto.getCommentGrowthPoint());
        memberLevel.setPriviledgeFreeFreight(memberLevelDto.getPriviledgeFreeFreight());
        memberLevel.setPriviledgeMemberPrice(memberLevelDto.getPriviledgeMemberPrice());
        memberLevel.setPriviledgeBirthday(memberLevelDto.getPriviledgeBirthday());
        memberLevel.setNote(memberLevelDto.getNote());
        memberLevel.insert();
        return memberLevel;
    }

    /**
     * 更新会员等级
     * @param memberLevelDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MemberLevel update(MemberLevelDto memberLevelDto) {
        MemberLevel memberLevel = baseMapper.selectById(memberLevelDto.getId());
        if (memberLevel == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(memberLevelDto.getName(), memberLevel::setName);
        JavaUtils.INSTANCE.acceptIfNotNull(memberLevelDto.getGrowthPoint(), memberLevel::setGrowthPoint);
        JavaUtils.INSTANCE.acceptIfNotNull(memberLevelDto.getDefaultStatus(), memberLevel::setDefaultStatus);
        JavaUtils.INSTANCE.acceptIfNotNull(memberLevelDto.getFreeFreightPoint(), memberLevel::setFreeFreightPoint);
        JavaUtils.INSTANCE.acceptIfNotNull(memberLevelDto.getCommentGrowthPoint(), memberLevel::setCommentGrowthPoint);
        JavaUtils.INSTANCE.acceptIfNotNull(memberLevelDto.getPriviledgeFreeFreight(), memberLevel::setPriviledgeFreeFreight);
        JavaUtils.INSTANCE.acceptIfNotNull(memberLevelDto.getPriviledgeMemberPrice(), memberLevel::setPriviledgeMemberPrice);
        JavaUtils.INSTANCE.acceptIfNotNull(memberLevelDto.getPriviledgeBirthday(), memberLevel::setPriviledgeBirthday);
        JavaUtils.INSTANCE.acceptIfNotNull(memberLevelDto.getNote(), memberLevel::setNote);
        memberLevel.updateById();
        return memberLevel;
    }

    /**
     * 删除会员等级
     * @param memberLevelDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MemberLevel delete(MemberLevelDto memberLevelDto) {
        MemberLevel memberLevel = baseMapper.selectById(memberLevelDto.getId());
        if (memberLevel == null) {
            return null;
        }
        memberLevel.deleteById();

        return memberLevel;
    }

    /**
     * 分页查询会员等级
     * @param memberLevelDto
     * @param memberLevelPage
     * @return
     */
    @Override
    public IPage<MemberLevel> queryPage(MemberLevelDto memberLevelDto, Page<MemberLevel> memberLevelPage) {

        return baseMapper.queryPage(memberLevelPage, memberLevelDto);
    }


    /**
     * 校验会员等级名称
     * @param memberLevelDto
     * @return
     */
    @Override
    public boolean checkName(MemberLevelDto memberLevelDto, boolean isAdd) {

        QueryWrapper<MemberLevel> memberLevelQueryWrapper = new QueryWrapper<MemberLevel>();
        memberLevelQueryWrapper.eq("name", memberLevelDto.getName());
        memberLevelQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        memberLevelQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            memberLevelQueryWrapper.ne("id", memberLevelDto.getId());
        }

        Integer count = baseMapper.selectCount(memberLevelQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有会员等级
     * @return
     */
    public List<MemberLevel> queryAll(MemberLevelDto memberLevelDto) {
        return baseMapper.queryAll(memberLevelDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberLevel> page = this.page(
                new Query<MemberLevel>().getPage(params),
                new QueryWrapper<MemberLevel>()
        );

        return new PageUtils(page);
    }



}
