package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.product.entity.PmsBrand;
import com.ypdaic.mymall.product.mapper.PmsBrandMapper;
import com.ypdaic.mymall.product.service.IPmsBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.PmsBrandDto;
import com.ypdaic.mymall.product.enums.PmsBrandExcelHeadersEnum;
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
 * 品牌 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements IPmsBrandService {


    /**
     * 新增品牌
     * @param pmsBrandDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsBrand add(PmsBrandDto pmsBrandDto) {

        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setBrandId(pmsBrandDto.getBrandId());
        pmsBrand.setName(pmsBrandDto.getName());
        pmsBrand.setLogo(pmsBrandDto.getLogo());
        pmsBrand.setDescript(pmsBrandDto.getDescript());
        pmsBrand.setShowStatus(pmsBrandDto.getShowStatus());
        pmsBrand.setFirstLetter(pmsBrandDto.getFirstLetter());
        pmsBrand.setSort(pmsBrandDto.getSort());
        pmsBrand.insert();
        return pmsBrand;
    }

    /**
     * 更新品牌
     * @param pmsBrandDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsBrand update(PmsBrandDto pmsBrandDto) {
        PmsBrand pmsBrand = baseMapper.selectById(pmsBrandDto.getId());
        if (pmsBrand == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(pmsBrandDto.getBrandId(), pmsBrand::setBrandId);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsBrandDto.getName(), pmsBrand::setName);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsBrandDto.getLogo(), pmsBrand::setLogo);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsBrandDto.getDescript(), pmsBrand::setDescript);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsBrandDto.getShowStatus(), pmsBrand::setShowStatus);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsBrandDto.getFirstLetter(), pmsBrand::setFirstLetter);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsBrandDto.getSort(), pmsBrand::setSort);
        pmsBrand.updateById();
        return pmsBrand;
    }

    /**
     * 删除品牌
     * @param pmsBrandDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsBrand delete(PmsBrandDto pmsBrandDto) {
        PmsBrand pmsBrand = baseMapper.selectById(pmsBrandDto.getId());
        if (pmsBrand == null) {
            return null;
        }
        pmsBrand.deleteById();

        return pmsBrand;
    }

    /**
     * 分页查询品牌
     * @param pmsBrandDto
     * @param pmsBrandPage
     * @return
     */
    @Override
    public IPage<PmsBrand> queryPage(PmsBrandDto pmsBrandDto, Page<PmsBrand> pmsBrandPage) {

        return baseMapper.queryPage(pmsBrandPage, pmsBrandDto);
    }


    /**
     * 校验品牌名称
     * @param pmsBrandDto
     * @return
     */
    @Override
    public boolean checkName(PmsBrandDto pmsBrandDto, boolean isAdd) {

        QueryWrapper<PmsBrand> pmsBrandQueryWrapper = new QueryWrapper<PmsBrand>();
        pmsBrandQueryWrapper.eq("name", pmsBrandDto.getName());
        pmsBrandQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        pmsBrandQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            pmsBrandQueryWrapper.ne("id", pmsBrandDto.getId());
        }

        Integer count = baseMapper.selectCount(pmsBrandQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有品牌
     * @return
     */
    public List<PmsBrand> queryAll(PmsBrandDto pmsBrandDto) {
        return baseMapper.queryAll(pmsBrandDto);
    }

}
