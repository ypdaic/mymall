package com.ypdaic.mymall.coupon.service.impl;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.coupon.entity.HomeAdv;
import com.ypdaic.mymall.coupon.mapper.HomeAdvMapper;
import com.ypdaic.mymall.coupon.service.IHomeAdvService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.coupon.vo.HomeAdvDto;
import com.ypdaic.mymall.coupon.enums.HomeAdvExcelHeadersEnum;
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
import java.util.Date;

/**
 * <p>
 * 首页轮播广告 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class HomeAdvServiceImpl extends ServiceImpl<HomeAdvMapper, HomeAdv> implements IHomeAdvService {


    /**
     * 新增首页轮播广告
     * @param homeAdvDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public HomeAdv add(HomeAdvDto homeAdvDto) {

        HomeAdv homeAdv = new HomeAdv();
        homeAdv.setName(homeAdvDto.getName());
        homeAdv.setPic(homeAdvDto.getPic());
        Date date2= new Date();
        homeAdv.setStartTime(date2);
        Date date3= new Date();
        homeAdv.setEndTime(date3);
        homeAdv.setStatus(homeAdvDto.getStatus());
        homeAdv.setClickCount(homeAdvDto.getClickCount());
        homeAdv.setUrl(homeAdvDto.getUrl());
        homeAdv.setNote(homeAdvDto.getNote());
        homeAdv.setSort(homeAdvDto.getSort());
        homeAdv.setPublisherId(homeAdvDto.getPublisherId());
        homeAdv.setAuthId(homeAdvDto.getAuthId());
        homeAdv.insert();
        return homeAdv;
    }

    /**
     * 更新首页轮播广告
     * @param homeAdvDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public HomeAdv update(HomeAdvDto homeAdvDto) {
        HomeAdv homeAdv = baseMapper.selectById(homeAdvDto.getId());
        if (homeAdv == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(homeAdvDto.getName(), homeAdv::setName);
        JavaUtils.INSTANCE.acceptIfNotNull(homeAdvDto.getPic(), homeAdv::setPic);
        JavaUtils.INSTANCE.acceptIfNotNull(homeAdvDto.getStartTime(), homeAdv::setStartTime);
        JavaUtils.INSTANCE.acceptIfNotNull(homeAdvDto.getEndTime(), homeAdv::setEndTime);
        JavaUtils.INSTANCE.acceptIfNotNull(homeAdvDto.getStatus(), homeAdv::setStatus);
        JavaUtils.INSTANCE.acceptIfNotNull(homeAdvDto.getClickCount(), homeAdv::setClickCount);
        JavaUtils.INSTANCE.acceptIfNotNull(homeAdvDto.getUrl(), homeAdv::setUrl);
        JavaUtils.INSTANCE.acceptIfNotNull(homeAdvDto.getNote(), homeAdv::setNote);
        JavaUtils.INSTANCE.acceptIfNotNull(homeAdvDto.getSort(), homeAdv::setSort);
        JavaUtils.INSTANCE.acceptIfNotNull(homeAdvDto.getPublisherId(), homeAdv::setPublisherId);
        JavaUtils.INSTANCE.acceptIfNotNull(homeAdvDto.getAuthId(), homeAdv::setAuthId);
        homeAdv.updateById();
        return homeAdv;
    }

    /**
     * 删除首页轮播广告
     * @param homeAdvDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public HomeAdv delete(HomeAdvDto homeAdvDto) {
        HomeAdv homeAdv = baseMapper.selectById(homeAdvDto.getId());
        if (homeAdv == null) {
            return null;
        }
        homeAdv.deleteById();

        return homeAdv;
    }

    /**
     * 分页查询首页轮播广告
     * @param homeAdvDto
     * @param homeAdvPage
     * @return
     */
    @Override
    public IPage<HomeAdv> queryPage(HomeAdvDto homeAdvDto, Page<HomeAdv> homeAdvPage) {

        return baseMapper.queryPage(homeAdvPage, homeAdvDto);
    }


    /**
     * 校验首页轮播广告名称
     * @param homeAdvDto
     * @return
     */
    @Override
    public boolean checkName(HomeAdvDto homeAdvDto, boolean isAdd) {

        QueryWrapper<HomeAdv> homeAdvQueryWrapper = new QueryWrapper<HomeAdv>();
        homeAdvQueryWrapper.eq("name", homeAdvDto.getName());
        homeAdvQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        homeAdvQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            homeAdvQueryWrapper.ne("id", homeAdvDto.getId());
        }

        Integer count = baseMapper.selectCount(homeAdvQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有首页轮播广告
     * @return
     */
    public List<HomeAdv> queryAll(HomeAdvDto homeAdvDto) {
        return baseMapper.queryAll(homeAdvDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HomeAdv> page = this.page(
                new Query<HomeAdv>().getPage(params),
                new QueryWrapper<HomeAdv>()
        );

        return new PageUtils(page);
    }

}
