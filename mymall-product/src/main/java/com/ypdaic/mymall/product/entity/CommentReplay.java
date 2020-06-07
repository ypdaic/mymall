package com.ypdaic.mymall.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ypdaic.mymall.common.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * 商品评价回复关系
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("pms_comment_replay")
public class CommentReplay extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    private Long commentId;

    /**
     * 回复id
     */
    private Long replyId;


}
