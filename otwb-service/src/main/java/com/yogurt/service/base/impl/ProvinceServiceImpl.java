package com.yogurt.service.base.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yogurt.dao.mapper.base.ProvinceMapper;
import com.yogurt.model.entity.base.Province;
import com.yogurt.model.vo.base.ProvinceVO;
import com.yogurt.service.base.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ProvinceServiceImpl
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-21 15:08
 */
@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public boolean addEntity(ProvinceVO provinceVO) {
        return provinceMapper.insertSelective(provinceVO.toEntity()) > 0;
    }

    @Override
    public boolean deleteEntity(ProvinceVO provinceVO) {
        return provinceMapper.delete(provinceVO.toEntity()) > 0;
    }

    @Override
    public boolean deleteEntityById(String id) {
        return provinceMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean updateEntity(ProvinceVO provinceVO) {
        return provinceMapper.updateByPrimaryKeySelective(provinceVO.toEntity()) > 0;
    }

    @Override
    public ProvinceVO findEntityById(String id) {
        return provinceMapper.selectByPrimaryKey(id).toVO();
    }

    @Override
    public PageInfo<ProvinceVO> findEntityListByPage(int pageNum, int pageSize) {
        PageInfo<Province> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> provinceMapper
                .selectAll());
        List<ProvinceVO> provinceVOList = new ArrayList<>();

        for (Province province : pageInfo.getList()) {
            provinceVOList.add(province.toVO());
        }
        PageInfo<ProvinceVO> voPageInfo = new PageInfo<>();
        voPageInfo.setList(provinceVOList);
        voPageInfo.setTotal(pageInfo.getTotal());

        return voPageInfo;
    }
}
