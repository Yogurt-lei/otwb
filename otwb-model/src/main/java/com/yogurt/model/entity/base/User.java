package com.yogurt.model.entity.base;

import com.yogurt.model.entity.BaseModel;
import com.yogurt.model.vo.base.UserVO;
import lombok.Data;

import javax.persistence.*;
import java.util.Optional;

@Data
@Table(name = "`dev_user`")
public class User extends BaseModel {
    @Id
    @Column(name = "`ID`")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),'-','') UUID FROM DUAL")
    private String id;

    @Column(name = "`USER_NAME`")
    private String userName;

    @Column(name = "`PASSWORD`")
    private String password;

    public UserVO toVO() {
        UserVO user = new UserVO();

        Optional.ofNullable(this.getId()).ifPresent(user::setId);
        Optional.ofNullable(this.getUserName()).ifPresent(user::setUserName);
        Optional.ofNullable(this.getPassword()).ifPresent(user::setPassword);

        return user;
    }
}