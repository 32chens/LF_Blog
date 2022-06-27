package com.chenlf.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 
 * @author ChenLF
 * @date 2022/06/27 23:18
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVo {

    private Long id;
    //所属分类id
    private Long categoryId;
    //所属分类名
    private String categoryName;
    private String content;
    private Date createTime;
    //是否允许评论 1是，0否
    private String isComment;
    private String title;
    //访问量
    private Long viewCount;
}
