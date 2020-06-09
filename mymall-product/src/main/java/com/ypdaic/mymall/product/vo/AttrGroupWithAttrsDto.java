package com.ypdaic.mymall.product.vo;

import com.ypdaic.mymall.product.entity.Attr;
import lombok.Data;

import java.util.List;

@Data
public class AttrGroupWithAttrsDto {
    /**
     * 分组id
     */
    private Long attrGroupId;
    /**
     * 组名
     */
    private String attrGroupName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    private String descript;
    /**
     * 组图标
     */
    private String icon;
    /**
     * 所属分类id
     */
    private Long catelogId;

    private List<Attr> attrs;

}
