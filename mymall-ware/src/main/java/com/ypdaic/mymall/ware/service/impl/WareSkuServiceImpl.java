package com.ypdaic.mymall.ware.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.ypdaic.mymall.common.util.*;
import com.ypdaic.mymall.fegin.product.IProductFeignService;
import com.ypdaic.mymall.ware.entity.WareSku;
import com.ypdaic.mymall.ware.mapper.WareSkuMapper;
import com.ypdaic.mymall.ware.service.IWareSkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.ware.vo.SkuHasStockVo;
import com.ypdaic.mymall.ware.vo.WareSkuDto;
import com.ypdaic.mymall.ware.enums.WareSkuExcelHeadersEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import com.ypdaic.mymall.common.enums.EnableEnum;

/**
 * <p>
 * 商品库存 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-11
 */
@Service
public class WareSkuServiceImpl extends ServiceImpl<WareSkuMapper, WareSku> implements IWareSkuService {

    @Autowired
    WareSkuMapper wareSkuDao;

    @Autowired
    IProductFeignService productFeignService;

    /**
     * 新增商品库存
     * @param wareSkuDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public WareSku add(WareSkuDto wareSkuDto) {

        WareSku wareSku = new WareSku();
        wareSku.setSkuId(wareSkuDto.getSkuId());
        wareSku.setWareId(wareSkuDto.getWareId());
        wareSku.setStock(wareSkuDto.getStock());
        wareSku.setSkuName(wareSkuDto.getSkuName());
        wareSku.setStockLocked(wareSkuDto.getStockLocked());
        wareSku.insert();
        return wareSku;
    }

    /**
     * 更新商品库存
     * @param wareSkuDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public WareSku update(WareSkuDto wareSkuDto) {
        WareSku wareSku = baseMapper.selectById(wareSkuDto.getId());
        if (wareSku == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(wareSkuDto.getSkuId(), wareSku::setSkuId);
        JavaUtils.INSTANCE.acceptIfNotNull(wareSkuDto.getWareId(), wareSku::setWareId);
        JavaUtils.INSTANCE.acceptIfNotNull(wareSkuDto.getStock(), wareSku::setStock);
        JavaUtils.INSTANCE.acceptIfNotNull(wareSkuDto.getSkuName(), wareSku::setSkuName);
        JavaUtils.INSTANCE.acceptIfNotNull(wareSkuDto.getStockLocked(), wareSku::setStockLocked);
        wareSku.updateById();
        return wareSku;
    }

    /**
     * 删除商品库存
     * @param wareSkuDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public WareSku delete(WareSkuDto wareSkuDto) {
        WareSku wareSku = baseMapper.selectById(wareSkuDto.getId());
        if (wareSku == null) {
            return null;
        }
        wareSku.deleteById();

        return wareSku;
    }

    /**
     * 分页查询商品库存
     * @param wareSkuDto
     * @param wareSkuPage
     * @return
     */
    @Override
    public IPage<WareSku> queryPage(WareSkuDto wareSkuDto, Page<WareSku> wareSkuPage) {

        return baseMapper.queryPage(wareSkuPage, wareSkuDto);
    }


    /**
     * 校验商品库存名称
     * @param wareSkuDto
     * @return
     */
    @Override
    public boolean checkName(WareSkuDto wareSkuDto, boolean isAdd) {

        QueryWrapper<WareSku> wareSkuQueryWrapper = new QueryWrapper<WareSku>();
        wareSkuQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        wareSkuQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            wareSkuQueryWrapper.ne("id", wareSkuDto.getId());
        }

        Integer count = baseMapper.selectCount(wareSkuQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有商品库存
     * @return
     */
    @Override
    public List<WareSku> queryAll(WareSkuDto wareSkuDto) {
        return baseMapper.queryAll(wareSkuDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        /**
         * skuId: 1
         * wareId: 2
         */
        QueryWrapper<WareSku> queryWrapper = new QueryWrapper<>();
        String skuId = (String) params.get("skuId");
        if(!StringUtils.isEmpty(skuId)){
            queryWrapper.eq("sku_id",skuId);
        }

        String wareId = (String) params.get("wareId");
        if(!StringUtils.isEmpty(wareId)){
            queryWrapper.eq("ware_id",wareId);
        }


        IPage<WareSku> page = this.page(
                new Query<WareSku>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public void addStock(Long skuId, Long wareId, Integer skuNum) {
        //1、判断如果还没有这个库存记录新增
        List<WareSku> entities = wareSkuDao.selectList(new QueryWrapper<WareSku>().eq("sku_id", skuId).eq("ware_id", wareId));
        if(entities == null || entities.size() == 0){
            WareSku skuEntity = new WareSku();
            skuEntity.setSkuId(skuId);
            skuEntity.setStock(skuNum);
            skuEntity.setWareId(wareId);
            skuEntity.setStockLocked(0);
            //TODO 远程查询sku的名字，如果失败，整个事务无需回滚
            //1、自己catch异常
            //TODO 还可以用什么办法让异常出现以后不回滚？高级
            try {
                R info = productFeignService.info(skuId);
                Map<String,Object> data = (Map<String, Object>) info.get("skuInfo");

                if(info.getCode() == 0){
                    skuEntity.setSkuName((String) data.get("skuName"));
                }
            }catch (Exception e){

            }


            wareSkuDao.insert(skuEntity);
        }else{
            wareSkuDao.addStock(skuId,wareId,skuNum);
        }

    }

    @Override
    public List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds) {

        List<SkuHasStockVo> skuHasStockVos = skuIds.stream().map(item -> {
            Long count = this.baseMapper.getSkuStock(item);
            SkuHasStockVo skuHasStockVo = new SkuHasStockVo();
            skuHasStockVo.setSkuId(item);
            skuHasStockVo.setHasStock(count == null?false:count > 0);
            return skuHasStockVo;
        }).collect(Collectors.toList());
        return skuHasStockVos;
    }

}
