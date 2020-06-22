package com.ypdaic.mymall.member.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.member.entity.MemberReceiveAddress;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.member.vo.MemberReceiveAddressDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 会员收货地址 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IMemberReceiveAddressService extends IService<MemberReceiveAddress> {

    /**
     * 新增会员收货地址
     * @param memberReceiveAddressDto
     * @return
     */
    MemberReceiveAddress add(MemberReceiveAddressDto memberReceiveAddressDto);

    /**
     * 更新会员收货地址
     * @param memberReceiveAddressDto
     * @return
     */
    MemberReceiveAddress update(MemberReceiveAddressDto memberReceiveAddressDto);

    /**
     * 删除会员收货地址
     * @param memberReceiveAddressDto
     * @return
     */
    MemberReceiveAddress delete(MemberReceiveAddressDto memberReceiveAddressDto);

    /**
     * 分页查询会员收货地址
     * @param memberReceiveAddressDto
     * @param memberReceiveAddressPage
     * @return
     */
    IPage<MemberReceiveAddress> queryPage(MemberReceiveAddressDto memberReceiveAddressDto, Page<MemberReceiveAddress> memberReceiveAddressPage);


    /**
     * 校验会员收货地址名称
     * @param memberReceiveAddressDto
     * @return
     */
    boolean checkName(MemberReceiveAddressDto memberReceiveAddressDto, boolean isAdd);

    /**
     *
     * 查询所有会员收货地址
     * @return
     */
    List<MemberReceiveAddress> queryAll(MemberReceiveAddressDto memberReceiveAddressDto);

    PageUtils queryPage(Map<String, Object> params);

    List<MemberReceiveAddress> getAddress(Long memberId);
}
