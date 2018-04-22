package com.yogurt.service.base.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yogurt.dao.mapper.base.UserMapper;
import com.yogurt.model.entity.base.User;
import com.yogurt.model.vo.base.UserVO;
import com.yogurt.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public boolean addUser(UserVO userVO) {
        return userMapper.insertSelective(userVO.toEntity()) > 0;
    }

    @Override
    public boolean deleteUser(UserVO userVO) {
        return userMapper.delete(userVO.toEntity()) > 0;
    }

    @Override
    public boolean deleteUserById(String id) {
        return userMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean updateUser(UserVO userVO) {
        return userMapper.updateByPrimaryKeySelective(userVO.toEntity()) > 0;
    }

    @Override
    public UserVO findUserById(String id) {
        return userMapper.selectByPrimaryKey(id).toVO();
    }

    @Override
    public List<UserVO> findUserListByPage(int pageNum, int pageSize) {
        PageInfo<User> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> userMapper
                .selectAll());
        List<UserVO> userList = new ArrayList<>();

        for (User user : pageInfo.getList()) {
            userList.add(user.toVO());
        }

        return userList;
    }
}
