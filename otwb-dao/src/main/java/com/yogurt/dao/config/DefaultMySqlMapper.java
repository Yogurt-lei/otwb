package com.yogurt.dao.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * DefaultMySqlMapper
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-22 17:05
 */
public interface DefaultMySqlMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
