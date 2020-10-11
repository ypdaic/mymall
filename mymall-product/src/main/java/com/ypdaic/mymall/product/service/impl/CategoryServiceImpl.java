package com.ypdaic.mymall.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ypdaic.mymall.common.annotation.MyCacheable;
import com.ypdaic.mymall.common.cache.FirstCacheTypeEnum;
import com.ypdaic.mymall.product.entity.Category;
import com.ypdaic.mymall.product.mapper.CategoryMapper;
import com.ypdaic.mymall.product.service.ICategoryBrandRelationService;
import com.ypdaic.mymall.product.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.Catalog3List;
import com.ypdaic.mymall.product.vo.CategoryDto;
import com.ypdaic.mymall.product.enums.CategoryExcelHeadersEnum;
import com.ypdaic.mymall.product.vo.Catelog2Vo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ypdaic.mymall.common.util.ExcelUtil;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import com.ypdaic.mymall.common.enums.EnableEnum;
import com.ypdaic.mymall.common.util.JavaUtils;

/**
 * <p>
 * 商品三级分类 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Slf4j
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    ICategoryBrandRelationService categoryBrandRelationService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedissonClient redissonClient;

    /**
     * 新增商品三级分类
     * @param categoryDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Category add(CategoryDto categoryDto) {

        Category category = new Category();
        category.setCatId(categoryDto.getCatId());
        category.setName(categoryDto.getName());
        category.setParentCid(categoryDto.getParentCid());
        category.setCatLevel(categoryDto.getCatLevel());
        category.setShowStatus(categoryDto.getShowStatus());
        category.setSort(categoryDto.getSort());
        category.setIcon(categoryDto.getIcon());
        category.setProductUnit(categoryDto.getProductUnit());
        category.setProductCount(categoryDto.getProductCount());
        category.insert();
        return category;
    }

    /**
     * 更新商品三级分类
     * @param categoryDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Category update(CategoryDto categoryDto) {
        Category category = baseMapper.selectById(categoryDto.getId());
        if (category == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(categoryDto.getCatId(), category::setCatId);
        JavaUtils.INSTANCE.acceptIfNotNull(categoryDto.getName(), category::setName);
        JavaUtils.INSTANCE.acceptIfNotNull(categoryDto.getParentCid(), category::setParentCid);
        JavaUtils.INSTANCE.acceptIfNotNull(categoryDto.getCatLevel(), category::setCatLevel);
        JavaUtils.INSTANCE.acceptIfNotNull(categoryDto.getShowStatus(), category::setShowStatus);
        JavaUtils.INSTANCE.acceptIfNotNull(categoryDto.getSort(), category::setSort);
        JavaUtils.INSTANCE.acceptIfNotNull(categoryDto.getIcon(), category::setIcon);
        JavaUtils.INSTANCE.acceptIfNotNull(categoryDto.getProductUnit(), category::setProductUnit);
        JavaUtils.INSTANCE.acceptIfNotNull(categoryDto.getProductCount(), category::setProductCount);
        category.updateById();
        return category;
    }

    /**
     * 删除商品三级分类
     * @param categoryDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Category delete(CategoryDto categoryDto) {
//        Category category = baseMapper.selectById(categoryDto.getId());
//        if (category == null) {
//            return null;
//        }
//        category.deleteById();
        baseMapper.deleteBatchIds(categoryDto.getIds());
        return null;
    }

    /**
     * 分页查询商品三级分类
     * @param categoryDto
     * @param categoryPage
     * @return
     */
    @Override
    public IPage<Category> queryPage(CategoryDto categoryDto, Page<Category> categoryPage) {

        return baseMapper.queryPage(categoryPage, categoryDto);
    }


    /**
     * 校验商品三级分类名称
     * @param categoryDto
     * @return
     */
    @Override
    public boolean checkName(CategoryDto categoryDto, boolean isAdd) {

        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<Category>();
        categoryQueryWrapper.eq("name", categoryDto.getName());
        categoryQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        categoryQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            categoryQueryWrapper.ne("id", categoryDto.getId());
        }

        Integer count = baseMapper.selectCount(categoryQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有商品三级分类
     * @return
     */
    public List<Category> queryAll(CategoryDto categoryDto) {
        return baseMapper.queryAll(categoryDto);
    }

    @Override
    public List<Category> listWithTree() {
        //1、查出所有分类
        List<Category> entities = baseMapper.selectList(null);

        //2、组装成父子的树形结构

        //2.1）、找到所有的一级分类
        List<Category> level1Menus = entities.stream().filter(categoryEntity ->
                categoryEntity.getParentCid() == 0
        ).map((menu)->{
            menu.setChildren(getChildrens(menu,entities));
            return menu;
        }).sorted((menu1,menu2)->{
            return (menu1.getSort()==null?0:menu1.getSort()) - (menu2.getSort()==null?0:menu2.getSort());
        }).collect(Collectors.toList());




        return level1Menus;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCascade(Category category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(),category.getName());
    }

    //递归查找所有菜单的子菜单
    private List<Category> getChildrens(Category root,List<Category> all){

        List<Category> children = all.stream().filter(categoryEntity -> {
            return categoryEntity.getParentCid().equals(root.getCatId());
        }).map(categoryEntity -> {
            List<Category> childrens = getChildrens(categoryEntity, all);
            if (CollectionUtils.isNotEmpty(childrens)) {
                //1、找到子菜单
                categoryEntity.setChildren(childrens);
            }

            return categoryEntity;
        }).sorted((menu1,menu2)->{
            //2、菜单的排序
            return (menu1.getSort()==null?0:menu1.getSort()) - (menu2.getSort()==null?0:menu2.getSort());
        }).collect(Collectors.toList());

        return children;
    }

    //[2,25,225]
    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();
        List<Long> parentPath = findParentPath(catelogId, paths);

        Collections.reverse(parentPath);


        return parentPath.toArray(new Long[parentPath.size()]);
    }

    /**
     * （1）根据一级分类，找到对应的二级分类
     * （2）将得到的二级分类，封装到Catelog2Vo中
     * （3）根据二级分类，得到对应的三级分类
     * （3）将三级分类封装到Catalog3List
     *  (4) 将执行结果放入到缓存中
     * @return
     */

//    @Cacheable(value = {"category"},key = "#root.methodName")
    @MyCacheable(value = {"category"},key = "#root.methodName", sync = true, useFirstCache = true, expireDate = 60L, useCustomExpireDate = true, firstCacheType = FirstCacheTypeEnum.ECACHE)
    @Override
    public Map<String, List<Catelog2Vo>> getCatelogJson() {
        log.info("查询数据库");
        //一次性查询出所有的分类数据，减少对于数据库的访问次数，后面的数据操作并不是到数据库中查询，而是直接从这个集合中获取，
        // 由于分类信息的数据量并不大，所以这种方式是可行的
        List<Category> categoryEntities = this.baseMapper.selectList(null);

        //1.查出所有一级分类
        List<Category> level1Categories = getParentCid(categoryEntities,0L);

        Map<String, List<Catelog2Vo>> parent_cid = level1Categories.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), level1 -> {
            //2. 根据一级分类的id查找到对应的二级分类
            List<Category> level2Categories = getParentCid(categoryEntities,level1.getCatId());

            //3. 根据二级分类，查找到对应的三级分类
            List<Catelog2Vo> catelog2Vos =null;

            if(null != level2Categories || level2Categories.size() > 0){
                catelog2Vos = level2Categories.stream().map(level2 -> {
                    //得到对应的三级分类
                    List<Category> level3Categories = getParentCid(categoryEntities,level2.getCatId());
                    //封装到Catalog3List
                    List<Catalog3List> catalog3Lists = null;
                    if (null != level3Categories) {
                        catalog3Lists = level3Categories.stream().map(level3 -> {
                            Catalog3List catalog3List = new Catalog3List(level2.getCatId().toString(), level3.getCatId().toString(), level3.getName());
                            return catalog3List;
                        }).collect(Collectors.toList());
                    }
                    return new Catelog2Vo(level1.getCatId().toString(), catalog3Lists, level2.getCatId().toString(), level2.getName());
                }).collect(Collectors.toList());
            }

            return catelog2Vos;
        }));
        return parent_cid;
    }

//    @Cacheable(value = {"category"},key = "'level1Categorys'")
    @MyCacheable(value = {"category"},key = "'level1Categorys'", sync = true, useFirstCache = true, expireDate = 60L, useCustomExpireDate = true, firstCacheType = FirstCacheTypeEnum.CAFFEINE)
    @Override
    public List<Category> getLevel1Categories() {
        log.info("查询一级分类数据");
        //找出一级分类
        List<Category> categoryEntities = this.baseMapper.selectList(new QueryWrapper<Category>().eq("cat_level", 1));
        return categoryEntities;
    }

    /**
     * 使用Redisson分布式锁来实现多个服务共享同一缓存中的数据
     * @return
     */
    public Map<String, List<Catelog2Vo>> getCatelogJsonFromDbWithRedissonLock() {

        RLock lock = redissonClient.getLock("CatelogJson-lock");
        //该方法会阻塞其他线程向下执行，只有释放锁之后才会接着向下执行
        lock.lock();
        Map<String, List<Catelog2Vo>> catelogJsonFromDb;
        try {
            //从数据库中查询分类数据
            catelogJsonFromDb = getCatelogJsonFromDb();
        } finally {
            lock.unlock();
        }

        return catelogJsonFromDb;

    }

    /**
     * 逻辑是
     * （0）首先查询Redis缓存中是否有分类数据信息，有则返回，否则继续执行
     * （1）根据一级分类，找到对应的二级分类
     * （2）将得到的二级分类，封装到Catelog2Vo中
     * （3）根据二级分类，得到对应的三级分类
     * （3）将三级分类封装到Catalog3List
     * （4）将查询结果放入到Redis中
     * @return
     */
    public Map<String, List<Catelog2Vo>> getCatelogJsonFromDb() {
        //先从redis缓存中查询，如果有数据，则返回查询结果
        String catelogJson = (String) redisTemplate.opsForValue().get("catelogJson");
        if(!StringUtils.isEmpty(catelogJson)){
            log.warn("从缓存中获取数据");
            return JSON.parseObject(catelogJson, new TypeReference<Map<String, List<Catelog2Vo>>>(){});
        }
        log.warn("查询数据库");
        //一次性查询出所有的分类数据，减少对于数据库的访问次数，后面的数据操作并不是到数据库中查询，而是直接从这个集合中获取，
        // 由于分类信息的数据量并不大，所以这种方式是可行的
        List<Category> categoryEntities = this.baseMapper.selectList(null);

        //1.查出所有一级分类
        List<Category> level1Categories = getParentCid(categoryEntities,0L);

        Map<String, List<Catelog2Vo>> parent_cid = level1Categories.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), level1 -> {
            //2. 根据一级分类的id查找到对应的二级分类
            List<Category> level2Categories = getParentCid(categoryEntities,level1.getCatId());

            //3. 根据二级分类，查找到对应的三级分类
            List<Catelog2Vo> catelog2Vos =null;

            if(null != level2Categories || level2Categories.size() > 0){
                catelog2Vos = level2Categories.stream().map(level2 -> {
                    //得到对应的三级分类
                    List<Category> level3Categories = getParentCid(categoryEntities,level2.getCatId());
                    //封装到Catalog3List
                    List<Catalog3List> catalog3Lists = null;
                    if (null != level3Categories) {
                        catalog3Lists = level3Categories.stream().map(level3 -> {
                            Catalog3List catalog3List = new Catalog3List(level2.getCatId().toString(), level3.getCatId().toString(), level3.getName());
                            return catalog3List;
                        }).collect(Collectors.toList());
                    }
                    return new Catelog2Vo(level1.getCatId().toString(), catalog3Lists, level2.getCatId().toString(), level2.getName());
                }).collect(Collectors.toList());
            }

            return catelog2Vos;
        }));

        //将查询结果放入到redis中
        redisTemplate.opsForValue().set("catelogJson",JSON.toJSONString(parent_cid),1, TimeUnit.DAYS);


        return parent_cid;
    }


    /**
     * 使用分布式锁来实现多个服务共享同一缓存中的数据
     * （1）设置读写锁，失败则表明其他线程先于该线程获取到了锁，则执行自旋，成功则表明获取到了锁
     * （2）获取锁成功，查询数据库，获取分类数据
     * （3）释放锁
     * @return
     */
    public Map<String, List<Catelog2Vo>> getCatelogJsonFromDbWithRedisLock() {
        String uuid= UUID.randomUUID().toString();
        //设置redis分布式锁，成功则返回true，否则返回false，该操作是原子性的
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", uuid, 300, TimeUnit.SECONDS);
        if(lock==null || !lock){
            //获取锁失败，重试
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            log.warn("获取锁失败，重新获取...");
            return getCatelogJsonFromDbWithRedisLock();
        }else{
            //获取锁成功
            log.warn("获取锁成功:)");
            Map<String, List<Catelog2Vo>> catelogJsonFromDb;
            try {
                //从数据库中查询分类数据
                catelogJsonFromDb = getCatelogJsonFromDb();
            } finally {
                //确保一定会释放锁
                String script="if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
                redisTemplate.execute(new DefaultRedisScript(script,Long.class),Arrays.asList("lock"),uuid);
                log.warn("释放锁成功:)");
            }
            return catelogJsonFromDb;
        }

    }

    /**
     * 在selectList中找到parentId等于传入的parentCid的所有分类数据
     * @param selectList
     * @param parentCid
     * @return
     */
    private List<Category> getParentCid(List<Category> selectList,Long parentCid) {
        List<Category> collect = selectList.stream().filter(item -> item.getParentCid() == parentCid).collect(Collectors.toList());
        return  collect;
    }


    //225,25,2
    private List<Long> findParentPath(Long catelogId,List<Long> paths){
        //1、收集当前节点id
        paths.add(catelogId);
        Category byId = this.getById(catelogId);
        if(byId.getParentCid()!=0){
            findParentPath(byId.getParentCid(),paths);
        }
        return paths;

    }

}
