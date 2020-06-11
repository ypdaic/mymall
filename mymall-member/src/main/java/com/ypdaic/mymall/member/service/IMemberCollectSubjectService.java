package com.ypdaic.mymall.member.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.member.entity.MemberCollectSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.member.vo.MemberCollectSubjectDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 会员收藏的专题活动 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IMemberCollectSubjectService extends IService<MemberCollectSubject> {

    /**
     * 新增会员收藏的专题活动
     * @param memberCollectSubjectDto
     * @return
     */
    MemberCollectSubject add(MemberCollectSubjectDto memberCollectSubjectDto);

    /**
     * 更新会员收藏的专题活动
     * @param memberCollectSubjectDto
     * @return
     */
    MemberCollectSubject update(MemberCollectSubjectDto memberCollectSubjectDto);

    /**
     * 删除会员收藏的专题活动
     * @param memberCollectSubjectDto
     * @return
     */
    MemberCollectSubject delete(MemberCollectSubjectDto memberCollectSubjectDto);

    /**
     * 分页查询会员收藏的专题活动
     * @param memberCollectSubjectDto
     * @param memberCollectSubjectPage
     * @return
     */
    IPage<MemberCollectSubject> queryPage(MemberCollectSubjectDto memberCollectSubjectDto, Page<MemberCollectSubject> memberCollectSubjectPage);


    /**
     * 校验会员收藏的专题活动名称
     * @param memberCollectSubjectDto
     * @return
     */
    boolean checkName(MemberCollectSubjectDto memberCollectSubjectDto, boolean isAdd);

    /**
     *
     * 查询所有会员收藏的专题活动
     * @return
     */
    List<MemberCollectSubject> queryAll(MemberCollectSubjectDto memberCollectSubjectDto);

    PageUtils queryPage(Map<String, Object> params);
}
