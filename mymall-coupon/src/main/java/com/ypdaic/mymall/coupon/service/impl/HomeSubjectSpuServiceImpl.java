package com.ypdaic.mymall.coupon.service.impl;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.coupon.entity.HomeSubjectSpu;
import com.ypdaic.mymall.coupon.mapper.HomeSubjectSpuMapper;
import com.ypdaic.mymall.coupon.service.IHomeSubjectSpuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.coupon.vo.HomeSubjectSpuDto;
import com.ypdaic.mymall.coupon.enums.HomeSubjectSpuExcelHeadersEnum;
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
 * 专题商品 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class HomeSubjectSpuServiceImpl extends ServiceImpl<HomeSubjectSpuMapper, HomeSubjectSpu> implements IHomeSubjectSpuService {


    /**
     * 新增专题商品
     * @param homeSubjectSpuDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public HomeSubjectSpu add(HomeSubjectSpuDto homeSubjectSpuDto) {

        HomeSubjectSpu homeSubjectSpu = new HomeSubjectSpu();
        homeSubjectSpu.setName(homeSubjectSpuDto.getName());
        homeSubjectSpu.setSubjectId(homeSubjectSpuDto.getSubjectId());
        homeSubjectSpu.setSpuId(homeSubjectSpuDto.getSpuId());
        homeSubjectSpu.setSort(homeSubjectSpuDto.getSort());
        homeSubjectSpu.insert();
        return homeSubjectSpu;
    }

    /**
     * 更新专题商品
     * @param homeSubjectSpuDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public HomeSubjectSpu update(HomeSubjectSpuDto homeSubjectSpuDto) {
        HomeSubjectSpu homeSubjectSpu = baseMapper.selectById(homeSubjectSpuDto.getId());
        if (homeSubjectSpu == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(homeSubjectSpuDto.getName(), homeSubjectSpu::setName);
        JavaUtils.INSTANCE.acceptIfNotNull(homeSubjectSpuDto.getSubjectId(), homeSubjectSpu::setSubjectId);
        JavaUtils.INSTANCE.acceptIfNotNull(homeSubjectSpuDto.getSpuId(), homeSubjectSpu::setSpuId);
        JavaUtils.INSTANCE.acceptIfNotNull(homeSubjectSpuDto.getSort(), homeSubjectSpu::setSort);
        homeSubjectSpu.updateById();
        return homeSubjectSpu;
    }

    /**
     * 删除专题商品
     * @param homeSubjectSpuDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public HomeSubjectSpu delete(HomeSubjectSpuDto homeSubjectSpuDto) {
        HomeSubjectSpu homeSubjectSpu = baseMapper.selectById(homeSubjectSpuDto.getId());
        if (homeSubjectSpu == null) {
            return null;
        }
        homeSubjectSpu.deleteById();

        return homeSubjectSpu;
    }

    /**
     * 分页查询专题商品
     * @param homeSubjectSpuDto
     * @param homeSubjectSpuPage
     * @return
     */
    @Override
    public IPage<HomeSubjectSpu> queryPage(HomeSubjectSpuDto homeSubjectSpuDto, Page<HomeSubjectSpu> homeSubjectSpuPage) {

        return baseMapper.queryPage(homeSubjectSpuPage, homeSubjectSpuDto);
    }


    /**
     * 校验专题商品名称
     * @param homeSubjectSpuDto
     * @return
     */
    @Override
    public boolean checkName(HomeSubjectSpuDto homeSubjectSpuDto, boolean isAdd) {

        QueryWrapper<HomeSubjectSpu> homeSubjectSpuQueryWrapper = new QueryWrapper<HomeSubjectSpu>();
        homeSubjectSpuQueryWrapper.eq("name", homeSubjectSpuDto.getName());
        homeSubjectSpuQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        homeSubjectSpuQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            homeSubjectSpuQueryWrapper.ne("id", homeSubjectSpuDto.getId());
        }

        Integer count = baseMapper.selectCount(homeSubjectSpuQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有专题商品
     * @return
     */
    public List<HomeSubjectSpu> queryAll(HomeSubjectSpuDto homeSubjectSpuDto) {
        return baseMapper.queryAll(homeSubjectSpuDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HomeSubjectSpu> page = this.page(
                new Query<HomeSubjectSpu>().getPage(params),
                new QueryWrapper<HomeSubjectSpu>()
        );

        return new PageUtils(page);
    }


}
