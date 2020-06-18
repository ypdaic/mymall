package com.ypdaic.mymall.product.vo;

import com.ypdaic.mymall.product.vo.Attr;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class SpuItemAttrGroupVo {
    private String groupName;
    private List<Attr> attrs;
}
