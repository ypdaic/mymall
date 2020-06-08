package com.ypdaic.mymall.member.service;

import com.ypdaic.mymall.member.entity.MemberCollectSpu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.member.vo.MemberCollectSpuDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 会员收藏的商品 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IMemberCollectSpuService extends IService<MemberCollectSpu> {

    /**
     * 新增会员收藏的商品
     * @param memberCollectSpuDto
     * @return
     */
    MemberCollectSpu add(MemberCollectSpuDto memberCollectSpuDto);

    /**
     * 更新会员收藏的商品
     * @param memberCollectSpuDto
     * @return
     */
    MemberCollectSpu update(MemberCollectSpuDto memberCollectSpuDto);

    /**
     * 删除会员收藏的商品
     * @param memberCollectSpuDto
     * @return
     */
    MemberCollectSpu delete(MemberCollectSpuDto memberCollectSpuDto);

    /**
     * 分页查询会员收藏的商品
     * @param memberCollectSpuDto
     * @param memberCollectSpuPage
     * @return
     */
    IPage<MemberCollectSpu> queryPage(MemberCollectSpuDto memberCollectSpuDto, Page<MemberCollectSpu> memberCollectSpuPage);


    /**
     * 校验会员收藏的商品名称
     * @param memberCollectSpuDto
     * @return
     */
    boolean checkName(MemberCollectSpuDto memberCollectSpuDto, boolean isAdd);

    /**
     *
     * 查询所有会员收藏的商品
     * @return
     */
    List<MemberCollectSpu> queryAll(MemberCollectSpuDto memberCollectSpuDto);
}
