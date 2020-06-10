package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.product.entity.SpuInfoDesc;
import com.ypdaic.mymall.product.mapper.SpuInfoDescMapper;
import com.ypdaic.mymall.product.service.ISpuInfoDescService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.SpuInfoDescDto;
import com.ypdaic.mymall.product.enums.SpuInfoDescExcelHeadersEnum;
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
 * spu信息介绍 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-10
 */
@Service
public class SpuInfoDescServiceImpl extends ServiceImpl<SpuInfoDescMapper, SpuInfoDesc> implements ISpuInfoDescService {


    /**
     * 新增spu信息介绍
     * @param spuInfoDescDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpuInfoDesc add(SpuInfoDescDto spuInfoDescDto) {

        SpuInfoDesc spuInfoDesc = new SpuInfoDesc();
        spuInfoDesc.setSpuId(spuInfoDescDto.getSpuId());
        spuInfoDesc.setDecript(spuInfoDescDto.getDecript());
        spuInfoDesc.insert();
        return spuInfoDesc;
    }

    /**
     * 更新spu信息介绍
     * @param spuInfoDescDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpuInfoDesc update(SpuInfoDescDto spuInfoDescDto) {
        SpuInfoDesc spuInfoDesc = baseMapper.selectById(spuInfoDescDto.getId());
        if (spuInfoDesc == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(spuInfoDescDto.getSpuId(), spuInfoDesc::setSpuId);
        JavaUtils.INSTANCE.acceptIfNotNull(spuInfoDescDto.getDecript(), spuInfoDesc::setDecript);
        spuInfoDesc.updateById();
        return spuInfoDesc;
    }

    /**
     * 删除spu信息介绍
     * @param spuInfoDescDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpuInfoDesc delete(SpuInfoDescDto spuInfoDescDto) {
        SpuInfoDesc spuInfoDesc = baseMapper.selectById(spuInfoDescDto.getId());
        if (spuInfoDesc == null) {
            return null;
        }
        spuInfoDesc.deleteById();

        return spuInfoDesc;
    }

    /**
     * 分页查询spu信息介绍
     * @param spuInfoDescDto
     * @param spuInfoDescPage
     * @return
     */
    @Override
    public IPage<SpuInfoDesc> queryPage(SpuInfoDescDto spuInfoDescDto, Page<SpuInfoDesc> spuInfoDescPage) {

        return baseMapper.queryPage(spuInfoDescPage, spuInfoDescDto);
    }


    /**
     * 校验spu信息介绍名称
     * @param spuInfoDescDto
     * @return
     */
    @Override
    public boolean checkName(SpuInfoDescDto spuInfoDescDto, boolean isAdd) {

        QueryWrapper<SpuInfoDesc> spuInfoDescQueryWrapper = new QueryWrapper<SpuInfoDesc>();
        spuInfoDescQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        spuInfoDescQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            spuInfoDescQueryWrapper.ne("id", spuInfoDescDto.getId());
        }

        Integer count = baseMapper.selectCount(spuInfoDescQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有spu信息介绍
     * @return
     */
    @Override
    public List<SpuInfoDesc> queryAll(SpuInfoDescDto spuInfoDescDto) {
        return baseMapper.queryAll(spuInfoDescDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoDesc> page = this.page(
                new Query<SpuInfoDesc>().getPage(params),
                new QueryWrapper<SpuInfoDesc>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSpuInfoDesc(SpuInfoDesc descEntity) {
        this.baseMapper.insert(descEntity);
    }

}
