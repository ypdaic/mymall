package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.product.entity.PmsCategory;
import com.ypdaic.mymall.product.mapper.PmsCategoryMapper;
import com.ypdaic.mymall.product.service.IPmsCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.PmsCategoryDto;
import com.ypdaic.mymall.product.enums.PmsCategoryExcelHeadersEnum;
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
 * 商品三级分类 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@Service
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryMapper, PmsCategory> implements IPmsCategoryService {


    /**
     * 新增商品三级分类
     * @param pmsCategoryDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsCategory add(PmsCategoryDto pmsCategoryDto) {

        PmsCategory pmsCategory = new PmsCategory();
        pmsCategory.setCatId(pmsCategoryDto.getCatId());
        pmsCategory.setName(pmsCategoryDto.getName());
        pmsCategory.setParentCid(pmsCategoryDto.getParentCid());
        pmsCategory.setCatLevel(pmsCategoryDto.getCatLevel());
        pmsCategory.setShowStatus(pmsCategoryDto.getShowStatus());
        pmsCategory.setSort(pmsCategoryDto.getSort());
        pmsCategory.setIcon(pmsCategoryDto.getIcon());
        pmsCategory.setProductUnit(pmsCategoryDto.getProductUnit());
        pmsCategory.setProductCount(pmsCategoryDto.getProductCount());
        pmsCategory.insert();
        return pmsCategory;
    }

    /**
     * 更新商品三级分类
     * @param pmsCategoryDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsCategory update(PmsCategoryDto pmsCategoryDto) {
        PmsCategory pmsCategory = baseMapper.selectById(pmsCategoryDto.getId());
        if (pmsCategory == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(pmsCategoryDto.getCatId(), pmsCategory::setCatId);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsCategoryDto.getName(), pmsCategory::setName);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsCategoryDto.getParentCid(), pmsCategory::setParentCid);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsCategoryDto.getCatLevel(), pmsCategory::setCatLevel);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsCategoryDto.getShowStatus(), pmsCategory::setShowStatus);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsCategoryDto.getSort(), pmsCategory::setSort);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsCategoryDto.getIcon(), pmsCategory::setIcon);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsCategoryDto.getProductUnit(), pmsCategory::setProductUnit);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsCategoryDto.getProductCount(), pmsCategory::setProductCount);
        pmsCategory.updateById();
        return pmsCategory;
    }

    /**
     * 删除商品三级分类
     * @param pmsCategoryDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsCategory delete(PmsCategoryDto pmsCategoryDto) {
        PmsCategory pmsCategory = baseMapper.selectById(pmsCategoryDto.getId());
        if (pmsCategory == null) {
            return null;
        }
        pmsCategory.deleteById();

        return pmsCategory;
    }

    /**
     * 分页查询商品三级分类
     * @param pmsCategoryDto
     * @param pmsCategoryPage
     * @return
     */
    @Override
    public IPage<PmsCategory> queryPage(PmsCategoryDto pmsCategoryDto, Page<PmsCategory> pmsCategoryPage) {

        return baseMapper.queryPage(pmsCategoryPage, pmsCategoryDto);
    }


    /**
     * 校验商品三级分类名称
     * @param pmsCategoryDto
     * @return
     */
    @Override
    public boolean checkName(PmsCategoryDto pmsCategoryDto, boolean isAdd) {

        QueryWrapper<PmsCategory> pmsCategoryQueryWrapper = new QueryWrapper<PmsCategory>();
        pmsCategoryQueryWrapper.eq("name", pmsCategoryDto.getName());
        pmsCategoryQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        pmsCategoryQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            pmsCategoryQueryWrapper.ne("id", pmsCategoryDto.getId());
        }

        Integer count = baseMapper.selectCount(pmsCategoryQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有商品三级分类
     * @return
     */
    public List<PmsCategory> queryAll(PmsCategoryDto pmsCategoryDto) {
        return baseMapper.queryAll(pmsCategoryDto);
    }

}
