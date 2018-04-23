package com.yogurt.model.vo;

import com.yogurt.model.entity.BaseModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    private String status;

    /**
     * 删除标志
     */
    private Boolean delFlag;

    /**
     * 创建日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;

    /**
     * 更新人
     */
    private String modifyUser;

    /**
     * VO to PO 子类覆写
     * BeanUtils.copyProperties 效率极低 不建议使用
     */
    public T toEntity() {
        return null;
    }
}
