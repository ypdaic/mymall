package com.ypdaic.mymall.member.service.impl;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.member.entity.MemberCollectSpu;
import com.ypdaic.mymall.member.entity.MemberCollectSubject;
import com.ypdaic.mymall.member.mapper.MemberCollectSubjectMapper;
import com.ypdaic.mymall.member.service.IMemberCollectSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.member.vo.MemberCollectSubjectDto;
import com.ypdaic.mymall.member.enums.MemberCollectSubjectExcelHeadersEnum;
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
 * 会员收藏的专题活动 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class MemberCollectSubjectServiceImpl extends ServiceImpl<MemberCollectSubjectMapper, MemberCollectSubject> implements IMemberCollectSubjectService {


    /**
     * 新增会员收藏的专题活动
     * @param memberCollectSubjectDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MemberCollectSubject add(MemberCollectSubjectDto memberCollectSubjectDto) {

        MemberCollectSubject memberCollectSubject = new MemberCollectSubject();
        memberCollectSubject.setSubjectId(memberCollectSubjectDto.getSubjectId());
        memberCollectSubject.setSubjectName(memberCollectSubjectDto.getSubjectName());
        memberCollectSubject.setSubjectImg(memberCollectSubjectDto.getSubjectImg());
        memberCollectSubject.setSubjectUrll(memberCollectSubjectDto.getSubjectUrll());
        memberCollectSubject.insert();
        return memberCollectSubject;
    }

    /**
     * 更新会员收藏的专题活动
     * @param memberCollectSubjectDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MemberCollectSubject update(MemberCollectSubjectDto memberCollectSubjectDto) {
        MemberCollectSubject memberCollectSubject = baseMapper.selectById(memberCollectSubjectDto.getId());
        if (memberCollectSubject == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(memberCollectSubjectDto.getSubjectId(), memberCollectSubject::setSubjectId);
        JavaUtils.INSTANCE.acceptIfNotNull(memberCollectSubjectDto.getSubjectName(), memberCollectSubject::setSubjectName);
        JavaUtils.INSTANCE.acceptIfNotNull(memberCollectSubjectDto.getSubjectImg(), memberCollectSubject::setSubjectImg);
        JavaUtils.INSTANCE.acceptIfNotNull(memberCollectSubjectDto.getSubjectUrll(), memberCollectSubject::setSubjectUrll);
        memberCollectSubject.updateById();
        return memberCollectSubject;
    }

    /**
     * 删除会员收藏的专题活动
     * @param memberCollectSubjectDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MemberCollectSubject delete(MemberCollectSubjectDto memberCollectSubjectDto) {
        MemberCollectSubject memberCollectSubject = baseMapper.selectById(memberCollectSubjectDto.getId());
        if (memberCollectSubject == null) {
            return null;
        }
        memberCollectSubject.deleteById();

        return memberCollectSubject;
    }

    /**
     * 分页查询会员收藏的专题活动
     * @param memberCollectSubjectDto
     * @param memberCollectSubjectPage
     * @return
     */
    @Override
    public IPage<MemberCollectSubject> queryPage(MemberCollectSubjectDto memberCollectSubjectDto, Page<MemberCollectSubject> memberCollectSubjectPage) {

        return baseMapper.queryPage(memberCollectSubjectPage, memberCollectSubjectDto);
    }


    /**
     * 校验会员收藏的专题活动名称
     * @param memberCollectSubjectDto
     * @return
     */
    @Override
    public boolean checkName(MemberCollectSubjectDto memberCollectSubjectDto, boolean isAdd) {

        QueryWrapper<MemberCollectSubject> memberCollectSubjectQueryWrapper = new QueryWrapper<MemberCollectSubject>();
        memberCollectSubjectQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        memberCollectSubjectQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            memberCollectSubjectQueryWrapper.ne("id", memberCollectSubjectDto.getId());
        }

        Integer count = baseMapper.selectCount(memberCollectSubjectQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有会员收藏的专题活动
     * @return
     */
    public List<MemberCollectSubject> queryAll(MemberCollectSubjectDto memberCollectSubjectDto) {
        return baseMapper.queryAll(memberCollectSubjectDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberCollectSubject> page = this.page(
                new Query<MemberCollectSubject>().getPage(params),
                new QueryWrapper<MemberCollectSubject>()
        );

        return new PageUtils(page);
    }

}
