package com.yogurt.model.vo.global;

import com.yogurt.model.entity.BaseModel;
import com.yogurt.model.vo.ViewObject;
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
public class VersionVO implements ViewObject {

    @ApiModelProperty("版本信息")
    private String version;


    @Override
    public BaseModel toEntity() {
        return null;
    }
}
