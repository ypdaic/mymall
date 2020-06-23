package com.ypdaic.mymall.ware.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.TypeReference;
import com.ypdaic.mymall.common.util.*;
import com.ypdaic.mymall.fegin.member.IMemberFeginService;
import com.ypdaic.mymall.ware.entity.WareInfo;
import com.ypdaic.mymall.ware.mapper.WareInfoMapper;
import com.ypdaic.mymall.ware.service.IWareInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.ware.vo.FareVo;
import com.ypdaic.mymall.ware.vo.MemberReceiveAddressVo;
import com.ypdaic.mymall.ware.vo.WareInfoDto;
import com.ypdaic.mymall.ware.enums.WareInfoExcelHeadersEnum;
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
 * 仓库信息 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class WareInfoServiceImpl extends ServiceImpl<WareInfoMapper, WareInfo> implements IWareInfoService {

    @Autowired
    IMemberFeginService memberFeginService;

    /**
     * 新增仓库信息
     * @param wareInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public WareInfo add(WareInfoDto wareInfoDto) {

        WareInfo wareInfo = new WareInfo();
        wareInfo.setName(wareInfoDto.getName());
        wareInfo.setAddress(wareInfoDto.getAddress());
        wareInfo.setAreacode(wareInfoDto.getAreacode());
        wareInfo.insert();
        return wareInfo;
    }

    /**
     * 更新仓库信息
     * @param wareInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public WareInfo update(WareInfoDto wareInfoDto) {
        WareInfo wareInfo = baseMapper.selectById(wareInfoDto.getId());
        if (wareInfo == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(wareInfoDto.getName(), wareInfo::setName);
        JavaUtils.INSTANCE.acceptIfNotNull(wareInfoDto.getAddress(), wareInfo::setAddress);
        JavaUtils.INSTANCE.acceptIfNotNull(wareInfoDto.getAreacode(), wareInfo::setAreacode);
        wareInfo.updateById();
        return wareInfo;
    }

    /**
     * 删除仓库信息
     * @param wareInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public WareInfo delete(WareInfoDto wareInfoDto) {
        WareInfo wareInfo = baseMapper.selectById(wareInfoDto.getId());
        if (wareInfo == null) {
            return null;
        }
        wareInfo.deleteById();

        return wareInfo;
    }

    /**
     * 分页查询仓库信息
     * @param wareInfoDto
     * @param wareInfoPage
     * @return
     */
    @Override
    public IPage<WareInfo> queryPage(WareInfoDto wareInfoDto, Page<WareInfo> wareInfoPage) {

        return baseMapper.queryPage(wareInfoPage, wareInfoDto);
    }


    /**
     * 校验仓库信息名称
     * @param wareInfoDto
     * @return
     */
    @Override
    public boolean checkName(WareInfoDto wareInfoDto, boolean isAdd) {

        QueryWrapper<WareInfo> wareInfoQueryWrapper = new QueryWrapper<WareInfo>();
        wareInfoQueryWrapper.eq("name", wareInfoDto.getName());
        wareInfoQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        wareInfoQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            wareInfoQueryWrapper.ne("id", wareInfoDto.getId());
        }

        Integer count = baseMapper.selectCount(wareInfoQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有仓库信息
     * @return
     */
    public List<WareInfo> queryAll(WareInfoDto wareInfoDto) {
        return baseMapper.queryAll(wareInfoDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<WareInfo> wareInfoEntityQueryWrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            wareInfoEntityQueryWrapper.eq("id",key).or()
                    .like("name",key)
                    .or().like("address",key)
                    .or().like("areacode",key);
        }

        IPage<WareInfo> page = this.page(
                new Query<WareInfo>().getPage(params),
                wareInfoEntityQueryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public FareVo getFare(Long id) {
        FareVo fareVo = new FareVo();
        R info = memberFeginService.info(id);
        MemberReceiveAddressVo data = info.getData("memberReceiveAddress", new TypeReference<MemberReceiveAddressVo>() {
        });

        String phone = data.getPhone();
        String substring = phone.substring(phone.length() - 1, phone.length());
        fareVo.setMemberReceiveAddressVo(data);
        fareVo.setFare(new BigDecimal(substring));
        return fareVo;

    }

}
