package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.product.entity.PmsCommentReplay;
import com.ypdaic.mymall.product.mapper.PmsCommentReplayMapper;
import com.ypdaic.mymall.product.service.IPmsCommentReplayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.PmsCommentReplayDto;
import com.ypdaic.mymall.product.enums.PmsCommentReplayExcelHeadersEnum;
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
 * 商品评价回复关系 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@Service
public class PmsCommentReplayServiceImpl extends ServiceImpl<PmsCommentReplayMapper, PmsCommentReplay> implements IPmsCommentReplayService {


    /**
     * 新增商品评价回复关系
     * @param pmsCommentReplayDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsCommentReplay add(PmsCommentReplayDto pmsCommentReplayDto) {

        PmsCommentReplay pmsCommentReplay = new PmsCommentReplay();
        pmsCommentReplay.setCommentId(pmsCommentReplayDto.getCommentId());
        pmsCommentReplay.setReplyId(pmsCommentReplayDto.getReplyId());
        pmsCommentReplay.insert();
        return pmsCommentReplay;
    }

    /**
     * 更新商品评价回复关系
     * @param pmsCommentReplayDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsCommentReplay update(PmsCommentReplayDto pmsCommentReplayDto) {
        PmsCommentReplay pmsCommentReplay = baseMapper.selectById(pmsCommentReplayDto.getId());
        if (pmsCommentReplay == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(pmsCommentReplayDto.getCommentId(), pmsCommentReplay::setCommentId);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsCommentReplayDto.getReplyId(), pmsCommentReplay::setReplyId);
        pmsCommentReplay.updateById();
        return pmsCommentReplay;
    }

    /**
     * 删除商品评价回复关系
     * @param pmsCommentReplayDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsCommentReplay delete(PmsCommentReplayDto pmsCommentReplayDto) {
        PmsCommentReplay pmsCommentReplay = baseMapper.selectById(pmsCommentReplayDto.getId());
        if (pmsCommentReplay == null) {
            return null;
        }
        pmsCommentReplay.deleteById();

        return pmsCommentReplay;
    }

    /**
     * 分页查询商品评价回复关系
     * @param pmsCommentReplayDto
     * @param pmsCommentReplayPage
     * @return
     */
    @Override
    public IPage<PmsCommentReplay> queryPage(PmsCommentReplayDto pmsCommentReplayDto, Page<PmsCommentReplay> pmsCommentReplayPage) {

        return baseMapper.queryPage(pmsCommentReplayPage, pmsCommentReplayDto);
    }


    /**
     * 校验商品评价回复关系名称
     * @param pmsCommentReplayDto
     * @return
     */
    @Override
    public boolean checkName(PmsCommentReplayDto pmsCommentReplayDto, boolean isAdd) {

        QueryWrapper<PmsCommentReplay> pmsCommentReplayQueryWrapper = new QueryWrapper<PmsCommentReplay>();
        pmsCommentReplayQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        pmsCommentReplayQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            pmsCommentReplayQueryWrapper.ne("id", pmsCommentReplayDto.getId());
        }

        Integer count = baseMapper.selectCount(pmsCommentReplayQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有商品评价回复关系
     * @return
     */
    public List<PmsCommentReplay> queryAll(PmsCommentReplayDto pmsCommentReplayDto) {
        return baseMapper.queryAll(pmsCommentReplayDto);
    }

}
