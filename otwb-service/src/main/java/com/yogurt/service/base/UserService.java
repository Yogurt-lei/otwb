package com.yogurt.service.base;

import com.yogurt.model.entity.base.User;

import java.util.List;

/**
 * UserService
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-21 15:07
 */
public interface UserService {
    boolean addUser(User user);

    List<User> findAllUser(int pageNum, int pageSize);
}
