package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.product.entity.SpuComment;
import com.ypdaic.mymall.product.mapper.SpuCommentMapper;
import com.ypdaic.mymall.product.service.ISpuCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.SpuCommentDto;
import com.ypdaic.mymall.product.enums.SpuCommentExcelHeadersEnum;
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
 * @since 2020-06-08
 */
@Service
public class SpuCommentServiceImpl extends ServiceImpl<SpuCommentMapper, SpuComment> implements ISpuCommentService {


    /**
     * 新增商品评价
     * @param spuCommentDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpuComment add(SpuCommentDto spuCommentDto) {

        SpuComment spuComment = new SpuComment();
        spuComment.setSkuId(spuCommentDto.getSkuId());
        spuComment.setSpuId(spuCommentDto.getSpuId());
        spuComment.setSpuName(spuCommentDto.getSpuName());
        spuComment.setMemberNickName(spuCommentDto.getMemberNickName());
        spuComment.setStar(spuCommentDto.getStar());
        spuComment.setMemberIp(spuCommentDto.getMemberIp());
        Date date6= new Date();
        spuComment.setCreateTime(date6);
        spuComment.setShowStatus(spuCommentDto.getShowStatus());
        spuComment.setSpuAttributes(spuCommentDto.getSpuAttributes());
        spuComment.setLikesCount(spuCommentDto.getLikesCount());
        spuComment.setReplyCount(spuCommentDto.getReplyCount());
        spuComment.setResources(spuCommentDto.getResources());
        spuComment.setContent(spuCommentDto.getContent());
        spuComment.setMemberIcon(spuCommentDto.getMemberIcon());
        spuComment.setCommentType(spuCommentDto.getCommentType());
        spuComment.insert();
        return spuComment;
    }

    /**
     * 更新商品评价
     * @param spuCommentDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpuComment update(SpuCommentDto spuCommentDto) {
        SpuComment spuComment = baseMapper.selectById(spuCommentDto.getId());
        if (spuComment == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(spuCommentDto.getSkuId(), spuComment::setSkuId);
        JavaUtils.INSTANCE.acceptIfNotNull(spuCommentDto.getSpuId(), spuComment::setSpuId);
        JavaUtils.INSTANCE.acceptIfNotNull(spuCommentDto.getSpuName(), spuComment::setSpuName);
        JavaUtils.INSTANCE.acceptIfNotNull(spuCommentDto.getMemberNickName(), spuComment::setMemberNickName);
        JavaUtils.INSTANCE.acceptIfNotNull(spuCommentDto.getStar(), spuComment::setStar);
        JavaUtils.INSTANCE.acceptIfNotNull(spuCommentDto.getMemberIp(), spuComment::setMemberIp);
        JavaUtils.INSTANCE.acceptIfNotNull(spuCommentDto.getShowStatus(), spuComment::setShowStatus);
        JavaUtils.INSTANCE.acceptIfNotNull(spuCommentDto.getSpuAttributes(), spuComment::setSpuAttributes);
        JavaUtils.INSTANCE.acceptIfNotNull(spuCommentDto.getLikesCount(), spuComment::setLikesCount);
        JavaUtils.INSTANCE.acceptIfNotNull(spuCommentDto.getReplyCount(), spuComment::setReplyCount);
        JavaUtils.INSTANCE.acceptIfNotNull(spuCommentDto.getResources(), spuComment::setResources);
        JavaUtils.INSTANCE.acceptIfNotNull(spuCommentDto.getContent(), spuComment::setContent);
        JavaUtils.INSTANCE.acceptIfNotNull(spuCommentDto.getMemberIcon(), spuComment::setMemberIcon);
        JavaUtils.INSTANCE.acceptIfNotNull(spuCommentDto.getCommentType(), spuComment::setCommentType);
        spuComment.updateById();
        return spuComment;
    }

    /**
     * 删除商品评价
     * @param spuCommentDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpuComment delete(SpuCommentDto spuCommentDto) {
        SpuComment spuComment = baseMapper.selectById(spuCommentDto.getId());
        if (spuComment == null) {
            return null;
        }
        spuComment.deleteById();

        return spuComment;
    }

    /**
     * 分页查询商品评价
     * @param spuCommentDto
     * @param spuCommentPage
     * @return
     */
    @Override
    public IPage<SpuComment> queryPage(SpuCommentDto spuCommentDto, Page<SpuComment> spuCommentPage) {

        return baseMapper.queryPage(spuCommentPage, spuCommentDto);
    }


    /**
     * 校验商品评价名称
     * @param spuCommentDto
     * @return
     */
    @Override
    public boolean checkName(SpuCommentDto spuCommentDto, boolean isAdd) {

        QueryWrapper<SpuComment> spuCommentQueryWrapper = new QueryWrapper<SpuComment>();
        spuCommentQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        spuCommentQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            spuCommentQueryWrapper.ne("id", spuCommentDto.getId());
        }

        Integer count = baseMapper.selectCount(spuCommentQueryWrapper);
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
    public List<SpuComment> queryAll(SpuCommentDto spuCommentDto) {
        return baseMapper.queryAll(spuCommentDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuComment> page = this.page(
                new Query<SpuComment>().getPage(params),
                new QueryWrapper<SpuComment>()
        );

        return new PageUtils(page);
    }

}
