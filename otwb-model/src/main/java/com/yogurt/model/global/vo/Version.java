package com.yogurt.model.global.vo;

import com.yogurt.model.global.ViewObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 版本信息
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-16 23:35
 */
@Getter
@Setter
@AllArgsConstructor
@ApiModel(description = "版本信息")
public class Version extends ViewObject {

    /**
     * 前缀
     */
    @ApiModelProperty("前缀")
    private String prefix;

    /**
     * 后缀
     */
    @ApiModelProperty("后缀")
    private String suffix;
}
