package com.ypdaic.mymall.product.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.ypdaic.mymall.common.to.SecKillSkuRedisTo;
import com.ypdaic.mymall.common.util.*;
import com.ypdaic.mymall.fegin.seckill.ISeckillFeignService;
import com.ypdaic.mymall.product.entity.SkuImages;
import com.ypdaic.mymall.product.entity.SkuInfo;
import com.ypdaic.mymall.product.entity.SpuInfoDesc;
import com.ypdaic.mymall.product.mapper.SkuInfoMapper;
import com.ypdaic.mymall.product.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.SkuInfoDto;
import com.ypdaic.mymall.product.enums.SkuInfoExcelHeadersEnum;
import com.ypdaic.mymall.product.vo.SkuItemSaleAttrVo;
import com.ypdaic.mymall.product.vo.SkuItemVo;
import com.ypdaic.mymall.product.vo.SpuItemAttrGroupVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.ypdaic.mymall.common.enums.EnableEnum;

/**
 * <p>
 * sku信息 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo> implements ISkuInfoService {

    @Autowired
    ISkuImagesService imagesService;

    @Autowired
    ISkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    ISpuInfoDescService spuInfoDescService;

    @Autowired
    IAttrGroupService attrGroupService;

    @Autowired
    ISeckillFeignService seckillFeignService;

    /**
     * 新增sku信息
     * @param skuInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SkuInfo add(SkuInfoDto skuInfoDto) {

        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setSkuId(skuInfoDto.getSkuId());
        skuInfo.setSpuId(skuInfoDto.getSpuId());
        skuInfo.setSkuName(skuInfoDto.getSkuName());
        skuInfo.setSkuDesc(skuInfoDto.getSkuDesc());
        skuInfo.setCatalogId(skuInfoDto.getCatalogId());
        skuInfo.setBrandId(skuInfoDto.getBrandId());
        skuInfo.setSkuDefaultImg(skuInfoDto.getSkuDefaultImg());
        skuInfo.setSkuTitle(skuInfoDto.getSkuTitle());
        skuInfo.setSkuSubtitle(skuInfoDto.getSkuSubtitle());
        skuInfo.setPrice(skuInfoDto.getPrice());
        skuInfo.setSaleCount(skuInfoDto.getSaleCount());
        skuInfo.insert();
        return skuInfo;
    }

    /**
     * 更新sku信息
     * @param skuInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SkuInfo update(SkuInfoDto skuInfoDto) {
        SkuInfo skuInfo = baseMapper.selectById(skuInfoDto.getId());
        if (skuInfo == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(skuInfoDto.getSkuId(), skuInfo::setSkuId);
        JavaUtils.INSTANCE.acceptIfNotNull(skuInfoDto.getSpuId(), skuInfo::setSpuId);
        JavaUtils.INSTANCE.acceptIfNotNull(skuInfoDto.getSkuName(), skuInfo::setSkuName);
        JavaUtils.INSTANCE.acceptIfNotNull(skuInfoDto.getSkuDesc(), skuInfo::setSkuDesc);
        JavaUtils.INSTANCE.acceptIfNotNull(skuInfoDto.getCatalogId(), skuInfo::setCatalogId);
        JavaUtils.INSTANCE.acceptIfNotNull(skuInfoDto.getBrandId(), skuInfo::setBrandId);
        JavaUtils.INSTANCE.acceptIfNotNull(skuInfoDto.getSkuDefaultImg(), skuInfo::setSkuDefaultImg);
        JavaUtils.INSTANCE.acceptIfNotNull(skuInfoDto.getSkuTitle(), skuInfo::setSkuTitle);
        JavaUtils.INSTANCE.acceptIfNotNull(skuInfoDto.getSkuSubtitle(), skuInfo::setSkuSubtitle);
        JavaUtils.INSTANCE.acceptIfNotNull(skuInfoDto.getPrice(), skuInfo::setPrice);
        JavaUtils.INSTANCE.acceptIfNotNull(skuInfoDto.getSaleCount(), skuInfo::setSaleCount);
        skuInfo.updateById();
        return skuInfo;
    }

    /**
     * 删除sku信息
     * @param skuInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SkuInfo delete(SkuInfoDto skuInfoDto) {
        SkuInfo skuInfo = baseMapper.selectById(skuInfoDto.getId());
        if (skuInfo == null) {
            return null;
        }
        skuInfo.deleteById();

        return skuInfo;
    }

    /**
     * 分页查询sku信息
     * @param skuInfoDto
     * @param skuInfoPage
     * @return
     */
    @Override
    public IPage<SkuInfo> queryPage(SkuInfoDto skuInfoDto, Page<SkuInfo> skuInfoPage) {

        return baseMapper.queryPage(skuInfoPage, skuInfoDto);
    }


    /**
     * 校验sku信息名称
     * @param skuInfoDto
     * @return
     */
    @Override
    public boolean checkName(SkuInfoDto skuInfoDto, boolean isAdd) {

        QueryWrapper<SkuInfo> skuInfoQueryWrapper = new QueryWrapper<SkuInfo>();
        skuInfoQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        skuInfoQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            skuInfoQueryWrapper.ne("id", skuInfoDto.getId());
        }

        Integer count = baseMapper.selectCount(skuInfoQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有sku信息
     * @return
     */
    @Override
    public List<SkuInfo> queryAll(SkuInfoDto skuInfoDto) {
        return baseMapper.queryAll(skuInfoDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfo> page = this.page(
                new Query<SkuInfo>().getPage(params),
                new QueryWrapper<SkuInfo>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSkuInfo(SkuInfo skuInfoEntity) {
        this.baseMapper.insert(skuInfoEntity);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SkuInfo> queryWrapper = new QueryWrapper<>();
        /**
         * key:
         * catelogId: 0
         * brandId: 0
         * min: 0
         * max: 0
         */
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            queryWrapper.and((wrapper)->{
                return wrapper.eq("sku_id",key).or().like("sku_name",key);
            });
        }

        String catelogId = (String) params.get("catelogId");
        if(!StringUtils.isEmpty(catelogId)&&!"0".equalsIgnoreCase(catelogId)){

            queryWrapper.eq("catalog_id",catelogId);
        }

        String brandId = (String) params.get("brandId");
        if(!StringUtils.isEmpty(brandId)&&!"0".equalsIgnoreCase(brandId)){
            queryWrapper.eq("brand_id",brandId);
        }

        String min = (String) params.get("min");
        if(!StringUtils.isEmpty(min)){
            queryWrapper.ge("price",min);
        }

        String max = (String) params.get("max");

        if(!StringUtils.isEmpty(max)  ){
            try{
                BigDecimal bigDecimal = new BigDecimal(max);

                if(bigDecimal.compareTo(new BigDecimal("0"))==1){
                    queryWrapper.le("price",max);
                }
            }catch (Exception e){

            }

        }


        IPage<SkuInfo> page = this.page(
                new Query<SkuInfo>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public List<SkuInfo> getSkusBySpuId(Long spuId) {
        QueryWrapper<SkuInfo> queryWrapper = new QueryWrapper<SkuInfo>().eq("spu_id", spuId);
        List<SkuInfo> skuInfoEntities = this.list(queryWrapper);
        return skuInfoEntities;
    }

    @Override
    public SkuItemVo item(Long skuId) {
        SkuItemVo skuItemVo = new SkuItemVo();

        //1. SKU基本信息获取，pms_sku_info
        SkuInfo byId = this.getById(skuId);
        skuItemVo.setInfo(byId);

        //2.SKU的图片信息获取，pms_sku_images
        List<SkuImages> skuImagesEntities=imagesService.getImagesBySkuId(skuId);
        skuItemVo.setImages(skuImagesEntities);

        //3. 获取SPU销售属性组合 pms_product_attr_value
        List<SkuItemSaleAttrVo> skuItemSaleAttrVos=skuSaleAttrValueService.getSaleAttrsBySpuId(byId.getSpuId());
        skuItemVo.setSaleAttr(skuItemSaleAttrVos);


        //4. 获取SPU的介绍 pms_spu_info_desc
        SpuInfoDesc spuInfoDescEntity = spuInfoDescService.getById(byId.getSpuId());
        skuItemVo.setDesp(spuInfoDescEntity);


        //5. 获取SPU的规格参数信息
        List<SpuItemAttrGroupVo> spuItemAttrGroupVos=attrGroupService.getAttrGroupWithAttrsBySpuId(byId.getSpuId(),byId.getCatalogId());
        skuItemVo.setGroupAttrs(spuItemAttrGroupVos);

        R skuSeckillInfo = seckillFeignService.getSkuSeckillInfo(skuId);
        int code = skuSeckillInfo.getCode();
        if (code == 0) {
            SecKillSkuRedisTo data = skuSeckillInfo.getData(new TypeReference<SecKillSkuRedisTo>() {
            });
            skuItemVo.setSecKillSkuRedisTo(data);
        }
        return skuItemVo;
    }

}
