package com.yogurt.model.entity.base;

import com.yogurt.model.entity.BaseModel;
import lombok.Data;

import javax.persistence.*;

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
}