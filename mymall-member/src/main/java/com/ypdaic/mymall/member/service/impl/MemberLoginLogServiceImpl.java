package com.ypdaic.mymall.member.service.impl;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.member.entity.MemberCollectSubject;
import com.ypdaic.mymall.member.entity.MemberLoginLog;
import com.ypdaic.mymall.member.mapper.MemberLoginLogMapper;
import com.ypdaic.mymall.member.service.IMemberLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.member.vo.MemberLoginLogDto;
import com.ypdaic.mymall.member.enums.MemberLoginLogExcelHeadersEnum;
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

/**
 * <p>
 * 会员登录记录 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class MemberLoginLogServiceImpl extends ServiceImpl<MemberLoginLogMapper, MemberLoginLog> implements IMemberLoginLogService {


    /**
     * 新增会员登录记录
     * @param memberLoginLogDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MemberLoginLog add(MemberLoginLogDto memberLoginLogDto) {

        MemberLoginLog memberLoginLog = new MemberLoginLog();
        memberLoginLog.setMemberId(memberLoginLogDto.getMemberId());
        Date date1= new Date();
        memberLoginLog.setCreateTime(date1);
        memberLoginLog.setIp(memberLoginLogDto.getIp());
        memberLoginLog.setCity(memberLoginLogDto.getCity());
        memberLoginLog.setLoginType(memberLoginLogDto.getLoginType());
        memberLoginLog.insert();
        return memberLoginLog;
    }

    /**
     * 更新会员登录记录
     * @param memberLoginLogDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MemberLoginLog update(MemberLoginLogDto memberLoginLogDto) {
        MemberLoginLog memberLoginLog = baseMapper.selectById(memberLoginLogDto.getId());
        if (memberLoginLog == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(memberLoginLogDto.getMemberId(), memberLoginLog::setMemberId);
        JavaUtils.INSTANCE.acceptIfNotNull(memberLoginLogDto.getIp(), memberLoginLog::setIp);
        JavaUtils.INSTANCE.acceptIfNotNull(memberLoginLogDto.getCity(), memberLoginLog::setCity);
        JavaUtils.INSTANCE.acceptIfNotNull(memberLoginLogDto.getLoginType(), memberLoginLog::setLoginType);
        memberLoginLog.updateById();
        return memberLoginLog;
    }

    /**
     * 删除会员登录记录
     * @param memberLoginLogDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MemberLoginLog delete(MemberLoginLogDto memberLoginLogDto) {
        MemberLoginLog memberLoginLog = baseMapper.selectById(memberLoginLogDto.getId());
        if (memberLoginLog == null) {
            return null;
        }
        memberLoginLog.deleteById();

        return memberLoginLog;
    }

    /**
     * 分页查询会员登录记录
     * @param memberLoginLogDto
     * @param memberLoginLogPage
     * @return
     */
    @Override
    public IPage<MemberLoginLog> queryPage(MemberLoginLogDto memberLoginLogDto, Page<MemberLoginLog> memberLoginLogPage) {

        return baseMapper.queryPage(memberLoginLogPage, memberLoginLogDto);
    }


    /**
     * 校验会员登录记录名称
     * @param memberLoginLogDto
     * @return
     */
    @Override
    public boolean checkName(MemberLoginLogDto memberLoginLogDto, boolean isAdd) {

        QueryWrapper<MemberLoginLog> memberLoginLogQueryWrapper = new QueryWrapper<MemberLoginLog>();
        memberLoginLogQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        memberLoginLogQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            memberLoginLogQueryWrapper.ne("id", memberLoginLogDto.getId());
        }

        Integer count = baseMapper.selectCount(memberLoginLogQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有会员登录记录
     * @return
     */
    public List<MemberLoginLog> queryAll(MemberLoginLogDto memberLoginLogDto) {
        return baseMapper.queryAll(memberLoginLogDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberLoginLog> page = this.page(
                new Query<MemberLoginLog>().getPage(params),
                new QueryWrapper<MemberLoginLog>()
        );

        return new PageUtils(page);
    }
}
