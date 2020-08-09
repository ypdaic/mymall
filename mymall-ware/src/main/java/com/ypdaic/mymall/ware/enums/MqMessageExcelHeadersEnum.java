package com.ypdaic.mymall.ware.enums;

/**
 *
 * 消息备份 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-08-08
 */
public enum MqMessageExcelHeadersEnum {

    MESSAGE_ID("message_id", "mq 消息id"),
    MESSAGE_TYPE("message_type", "mq 消息类型，0: 延时消息 1: 非延时消息"),
    CONTENT("content", "消息体"),
    TO_EXCHANGE("to_exchange", "交换器"),
    ROUTING_KEY("routing_key", "路由键"),
    CLASS_TYPE("class_type", "类型"),
    MESSAGE_STATUS("message_status", "0-新建 1-已发送 2-错误抵达 3-已抵达"),
    CREATE_TIME("create_time", "创建时间"),
    UPDATE_TIME("update_time", "更新时间");

    private String code;

    private String desc;

    MqMessageExcelHeadersEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}