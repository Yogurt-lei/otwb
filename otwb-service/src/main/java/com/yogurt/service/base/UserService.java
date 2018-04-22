package com.yogurt.service.base;

import com.yogurt.model.vo.base.UserVO;

import java.util.List;

/**
 * UserService
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-21 15:07
 */
public interface UserService {
    boolean addUser(UserVO userVO);

    boolean deleteUser(UserVO userVO);

    boolean deleteUserById(String id);

    boolean updateUser(UserVO userVO);

    UserVO findUserById(String id);

    /**
     * 分页查询
     */
    List<UserVO> findUserListByPage(int pageNum, int pageSize);
}
