package com.ypdaic.mymall.ware.mapper;

import com.ypdaic.mymall.ware.entity.MqMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.ware.vo.MqMessageDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 消息备份 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-08-08
 */
public interface MqMessageMapper extends BaseMapper<MqMessage> {

    /**
     * 分页查询消息备份
     * @param mqMessagePage
     * @param mqMessageDto
     * @return
     */
    IPage<MqMessage> queryPage(Page<MqMessage> mqMessagePage, @Param("dto") MqMessageDto mqMessageDto);

    /**
     * 导出查询数量
     * @param mqMessageDto
     * @return
     */
    Integer queryCount(@Param("dto") MqMessageDto mqMessageDto);

    /**
     * 导出分页查询消息备份
     * @param mqMessagePage
     * @param mqMessageDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> mqMessagePage, @Param("dto") MqMessageDto mqMessageDto);

    /**
     *
     * 查询所有消息备份
     * @return
     */
    List<MqMessage> queryAll(@Param("dto") MqMessageDto mqMessageDto);

}
