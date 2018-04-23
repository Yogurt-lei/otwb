package com.yogurt.model.entity;

import com.yogurt.model.vo.ViewObject;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.util.Date;

/**
 * PersistentObject
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-21 15:35
 */
@Data
public class BaseModel {
    /**
     * 状态
     */
    @Column(name = "`STATUS`")
    private String status;

    /**
     * 删除标志
     */
    @Column(name = "`DEL_FLAG`")
    private Boolean delFlag;

    /**
     * 创建日期
     */
    @Column(name = "`CREATE_DATE`")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * 创建人
     */
    @Column(name = "`CREATE_USER`")
    private String createUser;

    /**
     * 更新日期
     */
    @Column(name = "`MODIFY_DATE`")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;

    /**
     * 更新人
     */
    @Column(name = "`MODIFY_USER`")
    private String modifyUser;

    public ViewObject toVO(){
        return null;
    }
}
