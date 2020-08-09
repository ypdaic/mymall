package com.ypdaic.mymall.message.mapper;

import com.ypdaic.mymall.message.entity.RpTransactionMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.message.vo.RpTransactionMessageDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-08-08
 */
public interface RpTransactionMessageMapper extends BaseMapper<RpTransactionMessage> {

    /**
     * 分页查询
     * @param rpTransactionMessagePage
     * @param rpTransactionMessageDto
     * @return
     */
    IPage<RpTransactionMessage> queryPage(Page<RpTransactionMessage> rpTransactionMessagePage, @Param("dto") RpTransactionMessageDto rpTransactionMessageDto);

    /**
     * 导出查询数量
     * @param rpTransactionMessageDto
     * @return
     */
    Integer queryCount(@Param("dto") RpTransactionMessageDto rpTransactionMessageDto);

    /**
     * 导出分页查询
     * @param rpTransactionMessagePage
     * @param rpTransactionMessageDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> rpTransactionMessagePage, @Param("dto") RpTransactionMessageDto rpTransactionMessageDto);

    /**
     *
     * 查询所有
     * @return
     */
    List<RpTransactionMessage> queryAll(@Param("dto") RpTransactionMessageDto rpTransactionMessageDto);

}
