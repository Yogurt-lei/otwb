package com.yogurt.service.base.impl;

import com.yogurt.dao.mapper.base.UserMapper;
import com.yogurt.model.entity.base.User;
import com.yogurt.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserServiceImpl
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-21 15:08
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> findAllUser(int pageNum, int pageSize) {
        return userMapper.selectAll();
    }

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }
}
