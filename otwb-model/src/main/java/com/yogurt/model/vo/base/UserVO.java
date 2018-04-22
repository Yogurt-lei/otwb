package com.yogurt.model.vo.base;

import com.yogurt.model.entity.base.User;
import com.yogurt.model.vo.ViewObject;
import lombok.Data;

import java.util.Optional;

@Data
public class UserVO implements ViewObject<User> {
    private String id;

    private String userName;

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