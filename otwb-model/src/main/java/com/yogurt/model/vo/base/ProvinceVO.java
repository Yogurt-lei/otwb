package com.yogurt.model.vo.base;

import com.yogurt.model.entity.base.Province;
import com.yogurt.model.vo.ViewObject;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Optional;

@Data
public class ProvinceVO implements ViewObject<Province> {
    /**
     * ID
     */
    private String id;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

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

    @Override
    public Province toEntity() {
        Province province = new Province();

        Optional.ofNullable(this.getId()).ifPresent(province::setId);
        Optional.ofNullable(this.getCode()).ifPresent(province::setCode);
        Optional.ofNullable(this.getName()).ifPresent(province::setName);
        Optional.ofNullable(this.getStatus()).ifPresent(province::setStatus);
        Optional.ofNullable(this.getDelFlag()).ifPresent(province::setDelFlag);
        Optional.ofNullable(this.getCreateDate()).ifPresent(province::setCreateDate);
        Optional.ofNullable(this.getCreateUser()).ifPresent(province::setCreateUser);
        Optional.ofNullable(this.getModifyDate()).ifPresent(province::setModifyDate);
        Optional.ofNullable(this.getModifyUser()).ifPresent(province::setModifyUser);

        return province;
    }
}