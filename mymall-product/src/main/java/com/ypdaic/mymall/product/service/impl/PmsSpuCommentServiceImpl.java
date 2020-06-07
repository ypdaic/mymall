package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.product.entity.PmsSpuComment;
import com.ypdaic.mymall.product.mapper.PmsSpuCommentMapper;
import com.ypdaic.mymall.product.service.IPmsSpuCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.PmsSpuCommentDto;
import com.ypdaic.mymall.product.enums.PmsSpuCommentExcelHeadersEnum;
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
 * 商品评价 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@Service
public class PmsSpuCommentServiceImpl extends ServiceImpl<PmsSpuCommentMapper, PmsSpuComment> implements IPmsSpuCommentService {


    /**
     * 新增商品评价
     * @param pmsSpuCommentDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsSpuComment add(PmsSpuCommentDto pmsSpuCommentDto) {

        PmsSpuComment pmsSpuComment = new PmsSpuComment();
        pmsSpuComment.setSkuId(pmsSpuCommentDto.getSkuId());
        pmsSpuComment.setSpuId(pmsSpuCommentDto.getSpuId());
        pmsSpuComment.setSpuName(pmsSpuCommentDto.getSpuName());
        pmsSpuComment.setMemberNickName(pmsSpuCommentDto.getMemberNickName());
        pmsSpuComment.setStar(pmsSpuCommentDto.getStar());
        pmsSpuComment.setMemberIp(pmsSpuCommentDto.getMemberIp());
        Date date6= new Date();
        pmsSpuComment.setCreateTime(date6);
        pmsSpuComment.setShowStatus(pmsSpuCommentDto.getShowStatus());
        pmsSpuComment.setSpuAttributes(pmsSpuCommentDto.getSpuAttributes());
        pmsSpuComment.setLikesCount(pmsSpuCommentDto.getLikesCount());
        pmsSpuComment.setReplyCount(pmsSpuCommentDto.getReplyCount());
        pmsSpuComment.setResources(pmsSpuCommentDto.getResources());
        pmsSpuComment.setContent(pmsSpuCommentDto.getContent());
        pmsSpuComment.setMemberIcon(pmsSpuCommentDto.getMemberIcon());
        pmsSpuComment.setCommentType(pmsSpuCommentDto.getCommentType());
        pmsSpuComment.insert();
        return pmsSpuComment;
    }

    /**
     * 更新商品评价
     * @param pmsSpuCommentDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsSpuComment update(PmsSpuCommentDto pmsSpuCommentDto) {
        PmsSpuComment pmsSpuComment = baseMapper.selectById(pmsSpuCommentDto.getId());
        if (pmsSpuComment == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuCommentDto.getSkuId(), pmsSpuComment::setSkuId);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuCommentDto.getSpuId(), pmsSpuComment::setSpuId);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuCommentDto.getSpuName(), pmsSpuComment::setSpuName);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuCommentDto.getMemberNickName(), pmsSpuComment::setMemberNickName);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuCommentDto.getStar(), pmsSpuComment::setStar);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuCommentDto.getMemberIp(), pmsSpuComment::setMemberIp);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuCommentDto.getShowStatus(), pmsSpuComment::setShowStatus);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuCommentDto.getSpuAttributes(), pmsSpuComment::setSpuAttributes);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuCommentDto.getLikesCount(), pmsSpuComment::setLikesCount);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuCommentDto.getReplyCount(), pmsSpuComment::setReplyCount);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuCommentDto.getResources(), pmsSpuComment::setResources);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuCommentDto.getContent(), pmsSpuComment::setContent);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuCommentDto.getMemberIcon(), pmsSpuComment::setMemberIcon);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuCommentDto.getCommentType(), pmsSpuComment::setCommentType);
        pmsSpuComment.updateById();
        return pmsSpuComment;
    }

    /**
     * 删除商品评价
     * @param pmsSpuCommentDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsSpuComment delete(PmsSpuCommentDto pmsSpuCommentDto) {
        PmsSpuComment pmsSpuComment = baseMapper.selectById(pmsSpuCommentDto.getId());
        if (pmsSpuComment == null) {
            return null;
        }
        pmsSpuComment.deleteById();

        return pmsSpuComment;
    }

    /**
     * 分页查询商品评价
     * @param pmsSpuCommentDto
     * @param pmsSpuCommentPage
     * @return
     */
    @Override
    public IPage<PmsSpuComment> queryPage(PmsSpuCommentDto pmsSpuCommentDto, Page<PmsSpuComment> pmsSpuCommentPage) {

        return baseMapper.queryPage(pmsSpuCommentPage, pmsSpuCommentDto);
    }


    /**
     * 校验商品评价名称
     * @param pmsSpuCommentDto
     * @return
     */
    @Override
    public boolean checkName(PmsSpuCommentDto pmsSpuCommentDto, boolean isAdd) {

        QueryWrapper<PmsSpuComment> pmsSpuCommentQueryWrapper = new QueryWrapper<PmsSpuComment>();
        pmsSpuCommentQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        pmsSpuCommentQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            pmsSpuCommentQueryWrapper.ne("id", pmsSpuCommentDto.getId());
        }

        Integer count = baseMapper.selectCount(pmsSpuCommentQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有商品评价
     * @return
     */
    public List<PmsSpuComment> queryAll(PmsSpuCommentDto pmsSpuCommentDto) {
        return baseMapper.queryAll(pmsSpuCommentDto);
    }

}
