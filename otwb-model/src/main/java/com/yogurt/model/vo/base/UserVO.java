package com.yogurt.model.vo.base;

import com.yogurt.model.entity.base.User;
import com.yogurt.model.vo.ViewObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Optional;

@Data
@ApiModel("用户")
public class UserVO implements ViewObject<User> {

    @ApiModelProperty(hidden = true)
    private String id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

    @Override
    public User toEntity() {
        User user = new User();

        Optional.ofNullable(this.getId()).ifPresent(user::setId);
        Optional.ofNullable(this.getUserName()).ifPresent(user::setUserName);
        Optional.ofNullable(this.getPassword()).ifPresent(user::setPassword);

        return user;
    }
}