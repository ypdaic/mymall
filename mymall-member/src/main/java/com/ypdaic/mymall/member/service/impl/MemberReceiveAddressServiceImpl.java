package com.ypdaic.mymall.member.service.impl;

import com.ypdaic.mymall.member.entity.MemberReceiveAddress;
import com.ypdaic.mymall.member.mapper.MemberReceiveAddressMapper;
import com.ypdaic.mymall.member.service.IMemberReceiveAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.member.vo.MemberReceiveAddressDto;
import com.ypdaic.mymall.member.enums.MemberReceiveAddressExcelHeadersEnum;
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
 * 会员收货地址 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class MemberReceiveAddressServiceImpl extends ServiceImpl<MemberReceiveAddressMapper, MemberReceiveAddress> implements IMemberReceiveAddressService {


    /**
     * 新增会员收货地址
     * @param memberReceiveAddressDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MemberReceiveAddress add(MemberReceiveAddressDto memberReceiveAddressDto) {

        MemberReceiveAddress memberReceiveAddress = new MemberReceiveAddress();
        memberReceiveAddress.setMemberId(memberReceiveAddressDto.getMemberId());
        memberReceiveAddress.setName(memberReceiveAddressDto.getName());
        memberReceiveAddress.setPhone(memberReceiveAddressDto.getPhone());
        memberReceiveAddress.setPostCode(memberReceiveAddressDto.getPostCode());
        memberReceiveAddress.setProvince(memberReceiveAddressDto.getProvince());
        memberReceiveAddress.setCity(memberReceiveAddressDto.getCity());
        memberReceiveAddress.setRegion(memberReceiveAddressDto.getRegion());
        memberReceiveAddress.setDetailAddress(memberReceiveAddressDto.getDetailAddress());
        memberReceiveAddress.setAreacode(memberReceiveAddressDto.getAreacode());
        memberReceiveAddress.setDefaultStatus(memberReceiveAddressDto.getDefaultStatus());
        memberReceiveAddress.insert();
        return memberReceiveAddress;
    }

    /**
     * 更新会员收货地址
     * @param memberReceiveAddressDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MemberReceiveAddress update(MemberReceiveAddressDto memberReceiveAddressDto) {
        MemberReceiveAddress memberReceiveAddress = baseMapper.selectById(memberReceiveAddressDto.getId());
        if (memberReceiveAddress == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(memberReceiveAddressDto.getMemberId(), memberReceiveAddress::setMemberId);
        JavaUtils.INSTANCE.acceptIfNotNull(memberReceiveAddressDto.getName(), memberReceiveAddress::setName);
        JavaUtils.INSTANCE.acceptIfNotNull(memberReceiveAddressDto.getPhone(), memberReceiveAddress::setPhone);
        JavaUtils.INSTANCE.acceptIfNotNull(memberReceiveAddressDto.getPostCode(), memberReceiveAddress::setPostCode);
        JavaUtils.INSTANCE.acceptIfNotNull(memberReceiveAddressDto.getProvince(), memberReceiveAddress::setProvince);
        JavaUtils.INSTANCE.acceptIfNotNull(memberReceiveAddressDto.getCity(), memberReceiveAddress::setCity);
        JavaUtils.INSTANCE.acceptIfNotNull(memberReceiveAddressDto.getRegion(), memberReceiveAddress::setRegion);
        JavaUtils.INSTANCE.acceptIfNotNull(memberReceiveAddressDto.getDetailAddress(), memberReceiveAddress::setDetailAddress);
        JavaUtils.INSTANCE.acceptIfNotNull(memberReceiveAddressDto.getAreacode(), memberReceiveAddress::setAreacode);
        JavaUtils.INSTANCE.acceptIfNotNull(memberReceiveAddressDto.getDefaultStatus(), memberReceiveAddress::setDefaultStatus);
        memberReceiveAddress.updateById();
        return memberReceiveAddress;
    }

    /**
     * 删除会员收货地址
     * @param memberReceiveAddressDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MemberReceiveAddress delete(MemberReceiveAddressDto memberReceiveAddressDto) {
        MemberReceiveAddress memberReceiveAddress = baseMapper.selectById(memberReceiveAddressDto.getId());
        if (memberReceiveAddress == null) {
            return null;
        }
        memberReceiveAddress.deleteById();

        return memberReceiveAddress;
    }

    /**
     * 分页查询会员收货地址
     * @param memberReceiveAddressDto
     * @param memberReceiveAddressPage
     * @return
     */
    @Override
    public IPage<MemberReceiveAddress> queryPage(MemberReceiveAddressDto memberReceiveAddressDto, Page<MemberReceiveAddress> memberReceiveAddressPage) {

        return baseMapper.queryPage(memberReceiveAddressPage, memberReceiveAddressDto);
    }


    /**
     * 校验会员收货地址名称
     * @param memberReceiveAddressDto
     * @return
     */
    @Override
    public boolean checkName(MemberReceiveAddressDto memberReceiveAddressDto, boolean isAdd) {

        QueryWrapper<MemberReceiveAddress> memberReceiveAddressQueryWrapper = new QueryWrapper<MemberReceiveAddress>();
        memberReceiveAddressQueryWrapper.eq("name", memberReceiveAddressDto.getName());
        memberReceiveAddressQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        memberReceiveAddressQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            memberReceiveAddressQueryWrapper.ne("id", memberReceiveAddressDto.getId());
        }

        Integer count = baseMapper.selectCount(memberReceiveAddressQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有会员收货地址
     * @return
     */
    public List<MemberReceiveAddress> queryAll(MemberReceiveAddressDto memberReceiveAddressDto) {
        return baseMapper.queryAll(memberReceiveAddressDto);
    }

}
