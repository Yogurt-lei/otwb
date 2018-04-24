package com.yogurt.service;

import com.github.pagehelper.PageInfo;
import com.yogurt.model.vo.ViewObject;

/**
 * IBaseService 封装CRUD
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-24 21:26
 */
public interface IBaseService<T extends ViewObject> {
    boolean addEntity(T t);

    boolean deleteEntity(T t);

    boolean deleteEntityById(String id);

    boolean updateEntity(T t);

    T findEntityById(String id);

    /**
     * 分页查询
     */
    PageInfo<T> findEntityListByPage(int pageNum, int pageSize);
}
