package com.chenlf.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotArticleVo {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    //标题
    private String title;

    //访问量
    private Long viewCount;
}