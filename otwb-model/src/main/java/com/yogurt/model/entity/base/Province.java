package com.yogurt.model.entity.base;

import com.yogurt.model.entity.BaseModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "`dev_province`")
public class Province extends BaseModel {
    /**
     * ID
     */
    @Id
    @Column(name = "`ID`")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),'-','') UUID FROM DUAL")
    private String id;

    /**
     * 编码
     */
    @Column(name = "`CODE`")
    private String code;

    /**
     * 名称
     */
    @Column(name = "`NAME`")
    private String name;

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
}