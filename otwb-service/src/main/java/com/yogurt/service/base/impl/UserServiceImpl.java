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
    public boolean addEntity(UserVO userVO) {
        return userMapper.insertSelective(userVO.toEntity()) > 0;
    }

    @Override
    public boolean deleteEntity(UserVO userVO) {
        return userMapper.delete(userVO.toEntity()) > 0;
    }

    @Override
    public boolean deleteEntityById(String id) {
        return userMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean updateEntity(UserVO userVO) {
        return userMapper.updateByPrimaryKeySelective(userVO.toEntity()) > 0;
    }

    @Override
    public UserVO findEntityById(String id) {
        return userMapper.selectByPrimaryKey(id).toVO();
    }

    @Override
    public PageInfo<UserVO> findEntityListByPage(int pageNum, int pageSize) {
        PageInfo<User> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> userMapper
                .selectAll());
        List<UserVO> userVOList = new ArrayList<>();

        for (User user : pageInfo.getList()) {
            userVOList.add(user.toVO());
        }
        PageInfo<UserVO> voPageInfo = new PageInfo<>();
        voPageInfo.setList(userVOList);
        voPageInfo.setTotal(pageInfo.getTotal());

        return voPageInfo;
    }
}
