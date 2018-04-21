package com.yogurt.model.vo.base;

import com.yogurt.model.entity.base.User;
import com.yogurt.model.vo.ViewObject;
import lombok.Data;

import java.util.Optional;

/**
 * UserVO
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-21 15:31
 */
@Data
public class UserVO extends ViewObject {
    private Long id;

    private String userName;

    private String password;

    private String phone;

    @Override
    public User toEntity() {
        User user = new User();
        Optional.ofNullable(this.getId()).ifPresent(user::setId);
        Optional.ofNullable(this.getUserName()).ifPresent(user::setUserName);
        Optional.ofNullable(this.getPassword()).ifPresent(user::setPassword);
        Optional.ofNullable(this.getPhone()).ifPresent(user::setPhone);

        return user;
    }
}
