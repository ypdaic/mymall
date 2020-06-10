package com.ypdaic.mymall.coupon.service.impl;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.coupon.entity.SpuBounds;
import com.ypdaic.mymall.coupon.mapper.SpuBoundsMapper;
import com.ypdaic.mymall.coupon.service.ISpuBoundsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.coupon.vo.SpuBoundsDto;
import com.ypdaic.mymall.coupon.enums.SpuBoundsExcelHeadersEnum;
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
 * 商品spu积分设置 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-10
 */
@Service
public class SpuBoundsServiceImpl extends ServiceImpl<SpuBoundsMapper, SpuBounds> implements ISpuBoundsService {


    /**
     * 新增商品spu积分设置
     * @param spuBoundsDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpuBounds add(SpuBoundsDto spuBoundsDto) {

        SpuBounds spuBounds = new SpuBounds();
        spuBounds.setSpuId(spuBoundsDto.getSpuId());
        spuBounds.setGrowBounds(spuBoundsDto.getGrowBounds());
        spuBounds.setBuyBounds(spuBoundsDto.getBuyBounds());
        spuBounds.setWork(spuBoundsDto.getWork());
        spuBounds.insert();
        return spuBounds;
    }

    /**
     * 更新商品spu积分设置
     * @param spuBoundsDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpuBounds update(SpuBoundsDto spuBoundsDto) {
        SpuBounds spuBounds = baseMapper.selectById(spuBoundsDto.getId());
        if (spuBounds == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(spuBoundsDto.getSpuId(), spuBounds::setSpuId);
        JavaUtils.INSTANCE.acceptIfNotNull(spuBoundsDto.getGrowBounds(), spuBounds::setGrowBounds);
        JavaUtils.INSTANCE.acceptIfNotNull(spuBoundsDto.getBuyBounds(), spuBounds::setBuyBounds);
        JavaUtils.INSTANCE.acceptIfNotNull(spuBoundsDto.getWork(), spuBounds::setWork);
        spuBounds.updateById();
        return spuBounds;
    }

    /**
     * 删除商品spu积分设置
     * @param spuBoundsDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpuBounds delete(SpuBoundsDto spuBoundsDto) {
        SpuBounds spuBounds = baseMapper.selectById(spuBoundsDto.getId());
        if (spuBounds == null) {
            return null;
        }
        spuBounds.deleteById();

        return spuBounds;
    }

    /**
     * 分页查询商品spu积分设置
     * @param spuBoundsDto
     * @param spuBoundsPage
     * @return
     */
    @Override
    public IPage<SpuBounds> queryPage(SpuBoundsDto spuBoundsDto, Page<SpuBounds> spuBoundsPage) {

        return baseMapper.queryPage(spuBoundsPage, spuBoundsDto);
    }


    /**
     * 校验商品spu积分设置名称
     * @param spuBoundsDto
     * @return
     */
    @Override
    public boolean checkName(SpuBoundsDto spuBoundsDto, boolean isAdd) {

        QueryWrapper<SpuBounds> spuBoundsQueryWrapper = new QueryWrapper<SpuBounds>();
        spuBoundsQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        spuBoundsQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            spuBoundsQueryWrapper.ne("id", spuBoundsDto.getId());
        }

        Integer count = baseMapper.selectCount(spuBoundsQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有商品spu积分设置
     * @return
     */
    @Override
    public List<SpuBounds> queryAll(SpuBoundsDto spuBoundsDto) {
        return baseMapper.queryAll(spuBoundsDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuBounds> page = this.page(
                new Query<SpuBounds>().getPage(params),
                new QueryWrapper<SpuBounds>()
        );

        return new PageUtils(page);
    }

}
