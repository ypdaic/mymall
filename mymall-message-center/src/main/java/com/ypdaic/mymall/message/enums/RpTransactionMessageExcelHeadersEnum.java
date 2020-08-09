package com.ypdaic.mymall.message.enums;

/**
 *
 *  导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-08-08
 */
public enum RpTransactionMessageExcelHeadersEnum {

    VERSION("version", "版本号"),
    EDITOR("editor", "修改者"),
    CREATER("creater", "创建者"),
    EDIT_TIME("edit_time", "最后修改时间"),
    CREATE_TIME("create_time", "创建时间"),
    MESSAGE_ID("message_id", "消息ID"),
    MESSAGE_BODY("message_body", "消息内容"),
    MESSAGE_DATA_TYPE("message_data_type", "消息数据类型"),
    CONSUMER_QUEUE("consumer_queue", "消费队列"),
    MESSAGE_SEND_TIMES("message_send_times", "消息重发次数"),
    AREADLY_DEAD("areadly_dead", "是否死亡"),
    STATUS("status", "状态"),
    REMARK("remark", "备注"),
    FIELD1("field1", "扩展字段1"),
    FIELD2("field2", "扩展字段2"),
    FIELD3("field3", "扩展字段3");

    private String code;

    private String desc;

    RpTransactionMessageExcelHeadersEnum(String code, String desc) {
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