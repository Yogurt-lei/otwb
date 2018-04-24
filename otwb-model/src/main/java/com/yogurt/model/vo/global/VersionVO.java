package com.yogurt.model.vo.global;

import com.yogurt.model.entity.global.Version;
import com.yogurt.model.vo.ViewObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Optional;

/**
 * 版本信息
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-16 23:35
 */
@Data
@ApiModel("版本信息")
@EqualsAndHashCode(callSuper = true)
public class VersionVO extends ViewObject<Version> {
    @ApiModelProperty(hidden = true)
    String id;

    @ApiModelProperty("版本")
    String version;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dev_version
     *
     * @mbg.generated
     */
    @Override
    public Version toEntity() {
        Version version = new Version();

        Optional.ofNullable(this.getId()).ifPresent(version::setId);
        Optional.ofNullable(this.getVersion()).ifPresent(version::setVersion);
        Optional.ofNullable(this.getStatus()).ifPresent(version::setStatus);
        Optional.ofNullable(this.getDelFlag()).ifPresent(version::setDelFlag);
        Optional.ofNullable(this.getCreateDate()).ifPresent(version::setCreateDate);
        Optional.ofNullable(this.getCreateUser()).ifPresent(version::setCreateUser);
        Optional.ofNullable(this.getModifyDate()).ifPresent(version::setModifyDate);
        Optional.ofNullable(this.getModifyUser()).ifPresent(version::setModifyUser);

        return version;
    }
}