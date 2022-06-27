package com.chenlf.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author ChenLF
 * @date 2022/06/27 21:39
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVo {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long Id;

    private String name;
}
