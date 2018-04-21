package com.yogurt.model.entity.base;

import com.yogurt.model.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-21 15:06
 */
@Getter
@Setter
@Table(name = "t_user")
@NameStyle(Style.camelhumpAndUppercase)
public class User extends BaseModel {
    @Id
    private Long id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;
}
