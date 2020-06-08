package com.ypdaic.mymall.member.mapper;

import com.ypdaic.mymall.member.entity.MemberReceiveAddress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.member.vo.MemberReceiveAddressDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 会员收货地址 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface MemberReceiveAddressMapper extends BaseMapper<MemberReceiveAddress> {

    /**
     * 分页查询会员收货地址
     * @param memberReceiveAddressPage
     * @param memberReceiveAddressDto
     * @return
     */
    IPage<MemberReceiveAddress> queryPage(Page<MemberReceiveAddress> memberReceiveAddressPage, @Param("dto") MemberReceiveAddressDto memberReceiveAddressDto);

    /**
     * 导出查询数量
     * @param memberReceiveAddressDto
     * @return
     */
    Integer queryCount(@Param("dto") MemberReceiveAddressDto memberReceiveAddressDto);

    /**
     * 导出分页查询会员收货地址
     * @param memberReceiveAddressPage
     * @param memberReceiveAddressDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> memberReceiveAddressPage, @Param("dto") MemberReceiveAddressDto memberReceiveAddressDto);

    /**
     *
     * 查询所有会员收货地址
     * @return
     */
    List<MemberReceiveAddress> queryAll(@Param("dto") MemberReceiveAddressDto memberReceiveAddressDto);

}
