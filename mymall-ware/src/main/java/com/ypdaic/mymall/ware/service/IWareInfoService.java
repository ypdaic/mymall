package com.ypdaic.mymall.ware.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.ware.entity.WareInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.ware.vo.WareInfoDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 仓库信息 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IWareInfoService extends IService<WareInfo> {

    /**
     * 新增仓库信息
     * @param wareInfoDto
     * @return
     */
    WareInfo add(WareInfoDto wareInfoDto);

    /**
     * 更新仓库信息
     * @param wareInfoDto
     * @return
     */
    WareInfo update(WareInfoDto wareInfoDto);

    /**
     * 删除仓库信息
     * @param wareInfoDto
     * @return
     */
    WareInfo delete(WareInfoDto wareInfoDto);

    /**
     * 分页查询仓库信息
     * @param wareInfoDto
     * @param wareInfoPage
     * @return
     */
    IPage<WareInfo> queryPage(WareInfoDto wareInfoDto, Page<WareInfo> wareInfoPage);


    /**
     * 校验仓库信息名称
     * @param wareInfoDto
     * @return
     */
    boolean checkName(WareInfoDto wareInfoDto, boolean isAdd);

    /**
     *
     * 查询所有仓库信息
     * @return
     */
    List<WareInfo> queryAll(WareInfoDto wareInfoDto);

    PageUtils queryPage(Map<String, Object> params);
}
