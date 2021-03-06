package com.yogurt.model.global;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "响应数据")
public class ResponseMessage {

    /**
     * 返回代码
     */
    @ApiModelProperty("代码")
    private Integer code;

    /**
     * 返回信息
     */
    @ApiModelProperty("信息")
    private String msg;

    /**
     * 返回数据
     */
    @ApiModelProperty("数据")
    private ViewObject data;
}
