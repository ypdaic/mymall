package com.ypdaic.mymall.member.mapper;

import com.ypdaic.mymall.member.entity.MemberCollectSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.member.vo.MemberCollectSubjectDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 会员收藏的专题活动 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface MemberCollectSubjectMapper extends BaseMapper<MemberCollectSubject> {

    /**
     * 分页查询会员收藏的专题活动
     * @param memberCollectSubjectPage
     * @param memberCollectSubjectDto
     * @return
     */
    IPage<MemberCollectSubject> queryPage(Page<MemberCollectSubject> memberCollectSubjectPage, @Param("dto") MemberCollectSubjectDto memberCollectSubjectDto);

    /**
     * 导出查询数量
     * @param memberCollectSubjectDto
     * @return
     */
    Integer queryCount(@Param("dto") MemberCollectSubjectDto memberCollectSubjectDto);

    /**
     * 导出分页查询会员收藏的专题活动
     * @param memberCollectSubjectPage
     * @param memberCollectSubjectDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> memberCollectSubjectPage, @Param("dto") MemberCollectSubjectDto memberCollectSubjectDto);

    /**
     *
     * 查询所有会员收藏的专题活动
     * @return
     */
    List<MemberCollectSubject> queryAll(@Param("dto") MemberCollectSubjectDto memberCollectSubjectDto);

}
