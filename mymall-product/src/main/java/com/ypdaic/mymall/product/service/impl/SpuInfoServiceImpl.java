package com.ypdaic.mymall.product.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.TypeReference;
import com.ypdaic.mymall.common.constant.ProductConstant;
import com.ypdaic.mymall.common.to.SkuHasStockVo;
import com.ypdaic.mymall.common.to.SkuReductionTo;
import com.ypdaic.mymall.common.to.SpuBoundTo;
import com.ypdaic.mymall.common.to.es.SkuEsModel;
import com.ypdaic.mymall.common.util.*;
import com.ypdaic.mymall.fegin.coupon.ICouponFeignService;
import com.ypdaic.mymall.fegin.search.ISearchFeignService;
import com.ypdaic.mymall.fegin.ware.IWareFeignService;
import com.ypdaic.mymall.product.entity.*;
import com.ypdaic.mymall.product.vo.Attr;
import com.ypdaic.mymall.product.mapper.SpuInfoMapper;
import com.ypdaic.mymall.product.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.*;
import com.ypdaic.mymall.product.enums.SpuInfoExcelHeadersEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.math.BigDecimal;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import com.ypdaic.mymall.common.enums.EnableEnum;

import java.util.Date;
import java.util.stream.Collectors;

/**
 * <p>
 * spu信息 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfo> implements ISpuInfoService {

    @Autowired
    ISpuInfoDescService spuInfoDescService;

    @Autowired
    ISpuImagesService imagesService;

    @Autowired
    IAttrService attrService;

    @Autowired
    IProductAttrValueService attrValueService;

    @Autowired
    ISkuInfoService skuInfoService;

    @Autowired
    ISkuImagesService skuImagesService;

    @Autowired
    ISkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    ICouponFeignService couponFeignService;

    @Autowired
    IWareFeignService wareFeignService;

    @Autowired
    IBrandService brandService;

    @Autowired
    ICategoryService categoryService;

    @Autowired
    ISearchFeignService searchFeignService;


    /**
     * 新增spu信息
     * @param spuInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpuInfo add(SpuInfoDto spuInfoDto) {

        SpuInfo spuInfo = new SpuInfo();
        spuInfo.setSpuName(spuInfoDto.getSpuName());
        spuInfo.setSpuDescription(spuInfoDto.getSpuDescription());
        spuInfo.setCatalogId(spuInfoDto.getCatalogId());
        spuInfo.setBrandId(spuInfoDto.getBrandId());
        spuInfo.setWeight(spuInfoDto.getWeight());
        spuInfo.setPublishStatus(spuInfoDto.getPublishStatus());
        Date date6= new Date();
        spuInfo.setCreateTime(date6);
        Date date7= new Date();
        spuInfo.setUpdateTime(date7);
        spuInfo.insert();
        return spuInfo;
    }

    /**
     * 更新spu信息
     * @param spuInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpuInfo update(SpuInfoDto spuInfoDto) {
        SpuInfo spuInfo = baseMapper.selectById(spuInfoDto.getId());
        if (spuInfo == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(spuInfoDto.getSpuName(), spuInfo::setSpuName);
        JavaUtils.INSTANCE.acceptIfNotNull(spuInfoDto.getSpuDescription(), spuInfo::setSpuDescription);
        JavaUtils.INSTANCE.acceptIfNotNull(spuInfoDto.getCatalogId(), spuInfo::setCatalogId);
        JavaUtils.INSTANCE.acceptIfNotNull(spuInfoDto.getBrandId(), spuInfo::setBrandId);
        JavaUtils.INSTANCE.acceptIfNotNull(spuInfoDto.getWeight(), spuInfo::setWeight);
        JavaUtils.INSTANCE.acceptIfNotNull(spuInfoDto.getPublishStatus(), spuInfo::setPublishStatus);
        Date date7= new Date();
        spuInfo.setUpdateTime(date7);
        spuInfo.updateById();
        return spuInfo;
    }

    /**
     * 删除spu信息
     * @param spuInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpuInfo delete(SpuInfoDto spuInfoDto) {
        SpuInfo spuInfo = baseMapper.selectById(spuInfoDto.getId());
        if (spuInfo == null) {
            return null;
        }
        spuInfo.deleteById();

        return spuInfo;
    }

    /**
     * 分页查询spu信息
     * @param spuInfoDto
     * @param spuInfoPage
     * @return
     */
    @Override
    public IPage<SpuInfo> queryPage(SpuInfoDto spuInfoDto, Page<SpuInfo> spuInfoPage) {

        return baseMapper.queryPage(spuInfoPage, spuInfoDto);
    }


    /**
     * 校验spu信息名称
     * @param spuInfoDto
     * @return
     */
    @Override
    public boolean checkName(SpuInfoDto spuInfoDto, boolean isAdd) {

        QueryWrapper<SpuInfo> spuInfoQueryWrapper = new QueryWrapper<SpuInfo>();
        spuInfoQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        spuInfoQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            spuInfoQueryWrapper.ne("id", spuInfoDto.getId());
        }

        Integer count = baseMapper.selectCount(spuInfoQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有spu信息
     * @return
     */
    @Override
    public List<SpuInfo> queryAll(SpuInfoDto spuInfoDto) {
        return baseMapper.queryAll(spuInfoDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfo> page = this.page(
                new Query<SpuInfo>().getPage(params),
                new QueryWrapper<SpuInfo>()
        );

        return new PageUtils(page);
    }

    /**
     * //TODO 高级部分完善
     * @param vo
     */
    @Transactional
    @Override
    public void saveSpuInfo(SpuSaveVo vo) {

        //1、保存spu基本信息 pms_spu_info
        SpuInfo infoEntity = new SpuInfo();
        BeanUtils.copyProperties(vo,infoEntity);
        infoEntity.setCreateTime(new Date());
        infoEntity.setUpdateTime(new Date());
        this.saveBaseSpuInfo(infoEntity);

        //2、保存Spu的描述图片 pms_spu_info_desc
        List<String> decript = vo.getDecript();
        SpuInfoDesc descEntity = new SpuInfoDesc();
        descEntity.setSpuId(infoEntity.getId());
        descEntity.setDecript(String.join(",",decript));
        spuInfoDescService.saveSpuInfoDesc(descEntity);



        //3、保存spu的图片集 pms_spu_images
        List<String> images = vo.getImages();
        imagesService.saveImages(infoEntity.getId(),images);


        //4、保存spu的规格参数;pms_product_attr_value
        List<BaseAttrs> baseAttrs = vo.getBaseAttrs();
        List<ProductAttrValue> collect = baseAttrs.stream().map(attr -> {
            ProductAttrValue valueEntity = new ProductAttrValue();
            valueEntity.setAttrId(attr.getAttrId());
            com.ypdaic.mymall.product.entity.Attr id = attrService.getById(attr.getAttrId());
            valueEntity.setAttrName(id.getAttrName());
            valueEntity.setAttrValue(attr.getAttrValues());
            valueEntity.setQuickShow(attr.getShowDesc());
            valueEntity.setSpuId(infoEntity.getId());

            return valueEntity;
        }).collect(Collectors.toList());
        attrValueService.saveProductAttr(collect);


        //5、保存spu的积分信息；gulimall_sms->sms_spu_bounds
        Bounds bounds = vo.getBounds();
        SpuBoundTo spuBoundTo = new SpuBoundTo();
        BeanUtils.copyProperties(bounds,spuBoundTo);
        spuBoundTo.setSpuId(infoEntity.getId());
        R r = couponFeignService.saveSpuBounds(spuBoundTo);
        if(r.getCode() != 0){
            log.error("远程保存spu积分信息失败");
        }


        //5、保存当前spu对应的所有sku信息；

        List<Skus> skus = vo.getSkus();
        if(skus!=null && skus.size()>0){
            skus.forEach(item->{
                String defaultImg = "";
                for (Images image : item.getImages()) {
                    if(image.getDefaultImg() == 1){
                        defaultImg = image.getImgUrl();
                    }
                }
                //    private String skuName;
                //    private BigDecimal price;
                //    private String skuTitle;
                //    private String skuSubtitle;
                SkuInfo skuInfoEntity = new SkuInfo();
                BeanUtils.copyProperties(item,skuInfoEntity);
                skuInfoEntity.setBrandId(infoEntity.getBrandId());
                skuInfoEntity.setCatalogId(infoEntity.getCatalogId());
                skuInfoEntity.setSaleCount(0L);
                skuInfoEntity.setSpuId(infoEntity.getId());
                skuInfoEntity.setSkuDefaultImg(defaultImg);
                //5.1）、sku的基本信息；pms_sku_info
                skuInfoService.saveSkuInfo(skuInfoEntity);

                Long skuId = skuInfoEntity.getSkuId();

                // 保存sku 图片信息
                List<SkuImages> imagesEntities = item.getImages().stream().map(img -> {
                    SkuImages skuImagesEntity = new SkuImages();
                    skuImagesEntity.setSkuId(skuId);
                    skuImagesEntity.setImgUrl(img.getImgUrl());
                    skuImagesEntity.setDefaultImg(img.getDefaultImg());
                    return skuImagesEntity;
                }).filter(entity->{
                    //返回true就是需要，false就是剔除
                    return !StringUtils.isEmpty(entity.getImgUrl());
                }).collect(Collectors.toList());
                //5.2）、sku的图片信息；pms_sku_image
                skuImagesService.saveBatch(imagesEntities);
                //TODO 没有图片路径的无需保存

                List<Attr> attr = item.getAttr();
                List<SkuSaleAttrValue> skuSaleAttrValueEntities = attr.stream().map(a -> {
                    SkuSaleAttrValue attrValueEntity = new SkuSaleAttrValue();
                    BeanUtils.copyProperties(a, attrValueEntity);
                    attrValueEntity.setSkuId(skuId);

                    return attrValueEntity;
                }).collect(Collectors.toList());
                //5.3）、sku的销售属性信息：pms_sku_sale_attr_value
                skuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);

                // //5.4）、sku的优惠、满减等信息；gulimall_sms->sms_sku_ladder\sms_sku_full_reduction\sms_member_price
                SkuReductionTo skuReductionTo = new SkuReductionTo();
                BeanUtils.copyProperties(item,skuReductionTo);
                skuReductionTo.setSkuId(skuId);
                if(skuReductionTo.getFullCount() >0 || skuReductionTo.getFullPrice().compareTo(new BigDecimal("0")) == 1){
                    R r1 = couponFeignService.saveSkuReduction(skuReductionTo);
                    if(r1.getCode() != 0){
                        log.error("远程保存sku优惠信息失败");
                    }
                }



            });
        }






    }

    @Override
    public void saveBaseSpuInfo(SpuInfo infoEntity) {
        this.baseMapper.insert(infoEntity);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {

        QueryWrapper<SpuInfo> wrapper = new QueryWrapper<>();

        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            wrapper.and((w)->{
                return w.eq("id",key).or().like("spu_name",key);
            });
        }
        // status=1 and (id=1 or spu_name like xxx)
        String status = (String) params.get("status");
        if(!StringUtils.isEmpty(status)){
            wrapper.eq("publish_status",status);
        }

        String brandId = (String) params.get("brandId");
        if(!StringUtils.isEmpty(brandId)&&!"0".equalsIgnoreCase(brandId)){
            wrapper.eq("brand_id",brandId);
        }

        String catelogId = (String) params.get("catelogId");
        if(!StringUtils.isEmpty(catelogId)&&!"0".equalsIgnoreCase(catelogId)){
            wrapper.eq("catalog_id",catelogId);
        }

        /**
         * status: 2
         * key:
         * brandId: 9
         * catelogId: 225
         */

        IPage<SpuInfo> page = this.page(
                new Query<SpuInfo>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    /**
     * 整个业务逻辑就是围绕着封装SkuEsModel，并将它保存到ES中进行展开
     * 主要注意点是：
     * （1）Attrs中的所有属性都是可以检索的
     * （2）判断库存充足，需要借助于gulimall-ware来完成，通过openfeign远程调用对应方法
     * （3）将封装结果保存到ES中，即便存在重复调用的情况，也不会造成数据的重复插入，
     * 因为ES会比较插入文档的ID，相同则执行的是更新操作
     * @param spuId
     */
    @Override
    public void up(Long spuId) {

        //查询出supId所对应的SKU信息，品牌的名字
        List<SkuInfo> skuInfoEntities=skuInfoService.getSkusBySpuId(spuId);

        //TODO 4. 查询当前SKU的所有可以被用来检索的规格属性
        List<ProductAttrValue> productAttrValueEntities = attrValueService.baseAttrListForSpu(spuId);
        List<Long> attrIds = productAttrValueEntities.stream().map(item -> {
            Long attrId = item.getAttrId();
            return attrId;
        }).collect(Collectors.toList());

        //获取到支持检索的属性的属性ID
        List<Long> searchAttrIds = attrService.selectSearchAttrIds(attrIds);
        HashSet<Long> searchAttrIdsSet = new HashSet<>(searchAttrIds);
        //取得支持检索的的Attrs，用来封装SkuEsModel的attrs属性
        List<SkuEsModel.Attrs> attrsList = productAttrValueEntities.stream().filter(item -> {
            return searchAttrIdsSet.contains(item.getAttrId());
        }).map(item -> {
            SkuEsModel.Attrs attrs = new SkuEsModel.Attrs();
            attrs.setAttrId(item.getAttrId());
            attrs.setAttrName(item.getAttrName());
            attrs.setAttrValue(item.getAttrValue());
            return attrs;
        }).collect(Collectors.toList());

        //取得sku所对应的库存信息，即是否还有库存，为封装SkuEsModel的HasStock属性服务
        Map<Long, Boolean> stockMap = null;
        try {
            List<Long> skuInfoSkuIds = skuInfoEntities.stream().map(SkuInfo::getSkuId).collect(Collectors.toList());
            R skuHasStock = wareFeignService.getSkuHasStock(skuInfoSkuIds);

            stockMap = skuHasStock.getData(new TypeReference<List<SkuHasStockVo>>(){}).stream().collect(Collectors.toMap(SkuHasStockVo::getSkuId, SkuHasStockVo::getHasStock));
        } catch (Exception e) {
            log.error("库存服务查询异常：原因{}",e);
        }

        //封装每个SKU的信息
        Map<Long, Boolean> finalStockMap = stockMap;
        List<SkuEsModel> collect = skuInfoEntities.stream().map(item -> {
            //组装需要的数据
            SkuEsModel skuEsModel = new SkuEsModel();
            BeanUtils.copyProperties(item, skuEsModel);
            //skuImg skuPrice
            skuEsModel.setSkuPrice(item.getPrice());
            skuEsModel.setSkuImg(item.getSkuDefaultImg());

            //hasStock
            //TODo 1.发送远程调用，查询库存系统是否有库存
            if(finalStockMap == null){
                skuEsModel.setHasStock(true);
            }else{
                skuEsModel.setHasStock(finalStockMap.get(item.getSkuId()));
            }

            //hotScore
            //TODO 2. 热度评分，默认0
            skuEsModel.setHotScore(0L);

            //brandImg brandName   catelogName
            //TODO 3. 查询品牌和分类的名字信息
            Brand brandEntity = brandService.getById(skuEsModel.getBrandId());
            skuEsModel.setBrandName(brandEntity.getName());
            skuEsModel.setBrandImg(brandEntity.getLogo());

            //catalog和catelog命名不规范的坑
            skuEsModel.setCatelogId(item.getCatalogId());
            Category categoryEntity = categoryService.getById(skuEsModel.getCatelogId());
            skuEsModel.setCatelogName(categoryEntity.getName());

            //设置检索属性，attrs
            skuEsModel.setAttrs(attrsList);


            return skuEsModel;

        }).collect(Collectors.toList());

        //TODO 5. 将数据发送给ES进行保存；gulimall-search
        R statusUp = searchFeignService.productStatusUp(collect);
        if(statusUp.getCode() == 0){
            //远程调用成功
            //TODO 6.修改当前的SPU状态
            baseMapper.updateSpuStatus(spuId, ProductConstant.StatusEnum.SPU_UP.getCode());
        }else {
            //远程调用失败
            //TODO 7.重复调用的问题，接口幂等性


        }

    }

    @Override
    public SpuInfo getSpuInfoBySkuId(Long skuId) {
        SkuInfo skuInfo = skuInfoService.getById(skuId);
        SpuInfo spuInfo = baseMapper.selectById(skuInfo.getSpuId());
        return spuInfo;


    }

}
