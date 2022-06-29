package com.chenlf.vo.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author ChenLF
 * @date 2022/06/29 16:02
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentPageParam {

    private Long articleId;
    private Integer pageNum;
    private Integer pageSize;
}
