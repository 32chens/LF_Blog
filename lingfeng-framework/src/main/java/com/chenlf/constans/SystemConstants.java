package com.chenlf.constans;

import com.chenlf.vo.params.PageParam;

public class SystemConstants
{
    /**
     *  文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     *  文章是正常分布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;


    /**
     * 分类 状态
     */
    public static final  String STATUS_NORMAL = "0";

    /**
     * 友链状态为审核通过
     */
    public static final String  LINK_STATUS_NORMAL = "0";


    /**
     * 是否是管理员
     */
    public static final String  USER_TYPE = "1";

    /**
     * 根评论
     */
    public static final int COMMENT_ROOT = -1;

    /**
     * 评论类型为：文章评论
     */
    public static final String ARTICLE_COMMENT = "0";
    /**
     * 评论类型为：友联评论
     */
    public static final String LINK_COMMENT = "1";

}