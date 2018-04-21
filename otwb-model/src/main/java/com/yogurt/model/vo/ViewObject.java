package com.yogurt.model.vo;

import com.yogurt.model.entity.BaseModel;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * 页面对象
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-16 23:40
 */
@ApiModel(description = "页面对象")
public class ViewObject implements Serializable {

    /**
     * VO to PO
     * BeanUtils.copyProperties 效率极低 不建议使用
     */
    public BaseModel toEntity() {
        return null; // 子类覆写
    }
}
