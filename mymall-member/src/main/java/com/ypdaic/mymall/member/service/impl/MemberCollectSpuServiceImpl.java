package com.ypdaic.mymall.member.service.impl;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.member.entity.IntegrationChangeHistory;
import com.ypdaic.mymall.member.entity.MemberCollectSpu;
import com.ypdaic.mymall.member.mapper.MemberCollectSpuMapper;
import com.ypdaic.mymall.member.service.IMemberCollectSpuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.member.vo.MemberCollectSpuDto;
import com.ypdaic.mymall.member.enums.MemberCollectSpuExcelHeadersEnum;
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
 * 会员收藏的商品 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class MemberCollectSpuServiceImpl extends ServiceImpl<MemberCollectSpuMapper, MemberCollectSpu> implements IMemberCollectSpuService {


    /**
     * 新增会员收藏的商品
     * @param memberCollectSpuDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MemberCollectSpu add(MemberCollectSpuDto memberCollectSpuDto) {

        MemberCollectSpu memberCollectSpu = new MemberCollectSpu();
        memberCollectSpu.setMemberId(memberCollectSpuDto.getMemberId());
        memberCollectSpu.setSpuId(memberCollectSpuDto.getSpuId());
        memberCollectSpu.setSpuName(memberCollectSpuDto.getSpuName());
        memberCollectSpu.setSpuImg(memberCollectSpuDto.getSpuImg());
        Date date4= new Date();
        memberCollectSpu.setCreateTime(date4);
        memberCollectSpu.insert();
        return memberCollectSpu;
    }

    /**
     * 更新会员收藏的商品
     * @param memberCollectSpuDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MemberCollectSpu update(MemberCollectSpuDto memberCollectSpuDto) {
        MemberCollectSpu memberCollectSpu = baseMapper.selectById(memberCollectSpuDto.getId());
        if (memberCollectSpu == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(memberCollectSpuDto.getMemberId(), memberCollectSpu::setMemberId);
        JavaUtils.INSTANCE.acceptIfNotNull(memberCollectSpuDto.getSpuId(), memberCollectSpu::setSpuId);
        JavaUtils.INSTANCE.acceptIfNotNull(memberCollectSpuDto.getSpuName(), memberCollectSpu::setSpuName);
        JavaUtils.INSTANCE.acceptIfNotNull(memberCollectSpuDto.getSpuImg(), memberCollectSpu::setSpuImg);
        memberCollectSpu.updateById();
        return memberCollectSpu;
    }

    /**
     * 删除会员收藏的商品
     * @param memberCollectSpuDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MemberCollectSpu delete(MemberCollectSpuDto memberCollectSpuDto) {
        MemberCollectSpu memberCollectSpu = baseMapper.selectById(memberCollectSpuDto.getId());
        if (memberCollectSpu == null) {
            return null;
        }
        memberCollectSpu.deleteById();

        return memberCollectSpu;
    }

    /**
     * 分页查询会员收藏的商品
     * @param memberCollectSpuDto
     * @param memberCollectSpuPage
     * @return
     */
    @Override
    public IPage<MemberCollectSpu> queryPage(MemberCollectSpuDto memberCollectSpuDto, Page<MemberCollectSpu> memberCollectSpuPage) {

        return baseMapper.queryPage(memberCollectSpuPage, memberCollectSpuDto);
    }


    /**
     * 校验会员收藏的商品名称
     * @param memberCollectSpuDto
     * @return
     */
    @Override
    public boolean checkName(MemberCollectSpuDto memberCollectSpuDto, boolean isAdd) {

        QueryWrapper<MemberCollectSpu> memberCollectSpuQueryWrapper = new QueryWrapper<MemberCollectSpu>();
        memberCollectSpuQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        memberCollectSpuQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            memberCollectSpuQueryWrapper.ne("id", memberCollectSpuDto.getId());
        }

        Integer count = baseMapper.selectCount(memberCollectSpuQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有会员收藏的商品
     * @return
     */
    public List<MemberCollectSpu> queryAll(MemberCollectSpuDto memberCollectSpuDto) {
        return baseMapper.queryAll(memberCollectSpuDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberCollectSpu> page = this.page(
                new Query<MemberCollectSpu>().getPage(params),
                new QueryWrapper<MemberCollectSpu>()
        );

        return new PageUtils(page);
    }

}
