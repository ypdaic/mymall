package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.product.entity.Brand;
import com.ypdaic.mymall.product.mapper.BrandMapper;
import com.ypdaic.mymall.product.service.IBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.BrandDto;
import com.ypdaic.mymall.product.enums.BrandExcelHeadersEnum;
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
 * @since 2020-06-08
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {


    /**
     * 新增品牌
     * @param brandDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Brand add(BrandDto brandDto) {

        Brand brand = new Brand();
        brand.setBrandId(brandDto.getBrandId());
        brand.setName(brandDto.getName());
        brand.setLogo(brandDto.getLogo());
        brand.setDescript(brandDto.getDescript());
        brand.setShowStatus(brandDto.getShowStatus());
        brand.setFirstLetter(brandDto.getFirstLetter());
        brand.setSort(brandDto.getSort());
        brand.insert();
        return brand;
    }

    /**
     * 更新品牌
     * @param brandDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Brand update(BrandDto brandDto) {
        Brand brand = baseMapper.selectById(brandDto.getId());
        if (brand == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(brandDto.getBrandId(), brand::setBrandId);
        JavaUtils.INSTANCE.acceptIfNotNull(brandDto.getName(), brand::setName);
        JavaUtils.INSTANCE.acceptIfNotNull(brandDto.getLogo(), brand::setLogo);
        JavaUtils.INSTANCE.acceptIfNotNull(brandDto.getDescript(), brand::setDescript);
        JavaUtils.INSTANCE.acceptIfNotNull(brandDto.getShowStatus(), brand::setShowStatus);
        JavaUtils.INSTANCE.acceptIfNotNull(brandDto.getFirstLetter(), brand::setFirstLetter);
        JavaUtils.INSTANCE.acceptIfNotNull(brandDto.getSort(), brand::setSort);
        brand.updateById();
        return brand;
    }

    /**
     * 删除品牌
     * @param brandDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Brand delete(BrandDto brandDto) {
        Brand brand = baseMapper.selectById(brandDto.getId());
        if (brand == null) {
            return null;
        }
        brand.deleteById();

        return brand;
    }

    /**
     * 分页查询品牌
     * @param brandDto
     * @param brandPage
     * @return
     */
    @Override
    public IPage<Brand> queryPage(BrandDto brandDto, Page<Brand> brandPage) {

        return baseMapper.queryPage(brandPage, brandDto);
    }


    /**
     * 校验品牌名称
     * @param brandDto
     * @return
     */
    @Override
    public boolean checkName(BrandDto brandDto, boolean isAdd) {

        QueryWrapper<Brand> brandQueryWrapper = new QueryWrapper<Brand>();
        brandQueryWrapper.eq("name", brandDto.getName());
        brandQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        brandQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            brandQueryWrapper.ne("id", brandDto.getId());
        }

        Integer count = baseMapper.selectCount(brandQueryWrapper);
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
    public List<Brand> queryAll(BrandDto brandDto) {
        return baseMapper.queryAll(brandDto);
    }

}
