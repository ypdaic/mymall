package com.ypdaic.mymall.coupon.service.impl;

import com.ypdaic.mymall.coupon.entity.HomeSubject;
import com.ypdaic.mymall.coupon.mapper.HomeSubjectMapper;
import com.ypdaic.mymall.coupon.service.IHomeSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.coupon.vo.HomeSubjectDto;
import com.ypdaic.mymall.coupon.enums.HomeSubjectExcelHeadersEnum;
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
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class HomeSubjectServiceImpl extends ServiceImpl<HomeSubjectMapper, HomeSubject> implements IHomeSubjectService {


    /**
     * 新增首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @param homeSubjectDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public HomeSubject add(HomeSubjectDto homeSubjectDto) {

        HomeSubject homeSubject = new HomeSubject();
        homeSubject.setName(homeSubjectDto.getName());
        homeSubject.setTitle(homeSubjectDto.getTitle());
        homeSubject.setSubTitle(homeSubjectDto.getSubTitle());
        homeSubject.setStatus(homeSubjectDto.getStatus());
        homeSubject.setUrl(homeSubjectDto.getUrl());
        homeSubject.setSort(homeSubjectDto.getSort());
        homeSubject.setImg(homeSubjectDto.getImg());
        homeSubject.insert();
        return homeSubject;
    }

    /**
     * 更新首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @param homeSubjectDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public HomeSubject update(HomeSubjectDto homeSubjectDto) {
        HomeSubject homeSubject = baseMapper.selectById(homeSubjectDto.getId());
        if (homeSubject == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(homeSubjectDto.getName(), homeSubject::setName);
        JavaUtils.INSTANCE.acceptIfNotNull(homeSubjectDto.getTitle(), homeSubject::setTitle);
        JavaUtils.INSTANCE.acceptIfNotNull(homeSubjectDto.getSubTitle(), homeSubject::setSubTitle);
        JavaUtils.INSTANCE.acceptIfNotNull(homeSubjectDto.getStatus(), homeSubject::setStatus);
        JavaUtils.INSTANCE.acceptIfNotNull(homeSubjectDto.getUrl(), homeSubject::setUrl);
        JavaUtils.INSTANCE.acceptIfNotNull(homeSubjectDto.getSort(), homeSubject::setSort);
        JavaUtils.INSTANCE.acceptIfNotNull(homeSubjectDto.getImg(), homeSubject::setImg);
        homeSubject.updateById();
        return homeSubject;
    }

    /**
     * 删除首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @param homeSubjectDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public HomeSubject delete(HomeSubjectDto homeSubjectDto) {
        HomeSubject homeSubject = baseMapper.selectById(homeSubjectDto.getId());
        if (homeSubject == null) {
            return null;
        }
        homeSubject.deleteById();

        return homeSubject;
    }

    /**
     * 分页查询首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @param homeSubjectDto
     * @param homeSubjectPage
     * @return
     */
    @Override
    public IPage<HomeSubject> queryPage(HomeSubjectDto homeSubjectDto, Page<HomeSubject> homeSubjectPage) {

        return baseMapper.queryPage(homeSubjectPage, homeSubjectDto);
    }


    /**
     * 校验首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】名称
     * @param homeSubjectDto
     * @return
     */
    @Override
    public boolean checkName(HomeSubjectDto homeSubjectDto, boolean isAdd) {

        QueryWrapper<HomeSubject> homeSubjectQueryWrapper = new QueryWrapper<HomeSubject>();
        homeSubjectQueryWrapper.eq("name", homeSubjectDto.getName());
        homeSubjectQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        homeSubjectQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            homeSubjectQueryWrapper.ne("id", homeSubjectDto.getId());
        }

        Integer count = baseMapper.selectCount(homeSubjectQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @return
     */
    public List<HomeSubject> queryAll(HomeSubjectDto homeSubjectDto) {
        return baseMapper.queryAll(homeSubjectDto);
    }

}
