package com.yogurt.dao.config;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * DefaultMySqlMapper
 *
 * <pre>
 * 基础的CRUD操作为动态生成 复杂查询语句在mapper中自己定义
 *
 * 方法签名含有Example的支持模糊查询. 动态参数, 排序,去重,
 * List records = mapper.selectByExample(
 *      new Example.Builder(Country.class)
 *         .where(WeekendSqls.custom().andLike(Record::getXXX, "%a%")
 *         .andGreaterThan(Record::getYYY, "123"))
 *         .build());
 * => SELECT COLS FROM RECORD WHERE ( XXX like ? and YYY > ? )
 *
 * Create:   {@link tk.mybatis.mapper.provider.base.BaseInsertProvider}
 * Retrieve: {@link tk.mybatis.mapper.provider.base.BaseSelectProvider}
 * Update:   {@link tk.mybatis.mapper.provider.base.BaseUpdateProvider}
 * Delete:   {@link tk.mybatis.mapper.provider.base.BaseDeleteProvider}
 *
 * </pre>
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-22 17:05
 */
public interface DefaultMySqlMapper<T> extends Mapper<T>, MySqlMapper<T> {

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     */
    int insertSelective(T record);
    /**
     *  批量插入
     */
    int insertList(List<T> recordList);


    /**
     * 通过条件删除 null条件不会进行查询
     */
    int delete(T record);
    /**
     * 根据Example条件删除数据
     */
    int deleteByExample(Object example);
    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     */
    int deleteByPrimaryKey(String id);


    /**
     *  根据主键更新属性不为null的值
     */
    int updateByPrimaryKeySelective(T record);
    /**
     * 根据Example条件更新实体`record`包含的不是null的属性值
     */
    int updateByExampleSelective(@Param("record") T record, @Param("example") Object example);


    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常
     */
    T selectOne(T record);
    /**
     * 根据主键字段进行查询
     */
    T selectByPrimaryKey(String id);
    /**
     * 根据Example条件进行查询
     */
    T selectOneByExample(Object example);
    /**
     * 根据实体中的属性值进行查询
     */
    List<T> select(T record);
    /**
     * 根据Example条件进行查询
     */
    List<T> selectByExample(Object example);
    /**
     * 查询全部结果
     */
    List<T> selectAll();

    /**
     * 根据实体中的属性查询总数
     */
    int selectCount(T record);
    /**
     * 根据Example条件进行查询总数
     */
    int selectCountByExample(Object example);

}
