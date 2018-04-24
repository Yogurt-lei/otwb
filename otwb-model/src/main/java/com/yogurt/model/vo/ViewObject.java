package com.yogurt.model.vo;

import com.yogurt.model.entity.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 页面对象
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-16 23:40
 */
@ApiModel(description = "页面对象")
@Data
public class ViewObject<T extends BaseModel> implements Serializable {
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", position = 98)
    private String status;

    /**
     * 删除标志
     */
    @ApiModelProperty(value = "删除标志", dataType = "boolean", position = 99)
    private Boolean delFlag;

    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期", example = "2018-04-24 22:00:00", position = 100)
    private Date createDate;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", position = 101)
    private String createUser;

    /**
     * 更新日期
     */
    @ApiModelProperty(value = "更新日期", example = "2018-04-24 22:00:00", position = 102)
    private Date modifyDate;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人", position = 103)
    private String modifyUser;

    /**
     * VO to PO 子类覆写
     * BeanUtils.copyProperties 效率极低 不建议使用
     */
    public T toEntity() {
        return null;
    }
}
