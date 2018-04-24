package com.yogurt.service.global.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yogurt.dao.mapper.global.VersionMapper;
import com.yogurt.exception.BusinessException;
import com.yogurt.model.entity.global.Version;
import com.yogurt.model.vo.global.VersionVO;
import com.yogurt.service.global.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * VersionServiceImpl
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-21 15:07
 */
@Service
public class VersionServiceImpl implements VersionService {
    @Autowired
    private VersionMapper versionMapper;

    @Override
    public boolean addEntity(VersionVO versionVO) {
        return versionMapper.insertSelective(versionVO.toEntity()) > 0;
    }

    @Override
    public boolean deleteEntity(VersionVO versionVO) {
        return versionMapper.delete(versionVO.toEntity()) > 0;
    }

    @Override
    public boolean deleteEntityById(String id) {
        throw new BusinessException("版本信息不提供删除功能.");
    }

    @Override
    public boolean updateEntity(VersionVO versionVO) {
        return versionMapper.updateByPrimaryKeySelective(versionVO.toEntity()) > 0;
    }

    @Override
    public VersionVO findEntityById(String id) {
        return versionMapper.selectByPrimaryKey(id).toVO();
    }

    @Override
    public PageInfo<VersionVO> findEntityListByPage(int pageNum, int pageSize) {
        PageInfo<Version> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> versionMapper
                .selectAll());
        List<VersionVO> versionVOList = new ArrayList<>();

        for (Version version : pageInfo.getList()) {
            versionVOList.add(version.toVO());
        }
        PageInfo<VersionVO> voPageInfo = new PageInfo<>();
        voPageInfo.setList(versionVOList);
        voPageInfo.setTotal(pageInfo.getTotal());

        return voPageInfo;
    }
}
