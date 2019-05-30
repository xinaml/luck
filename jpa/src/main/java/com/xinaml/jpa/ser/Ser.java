/**
 * @author lgq
 * @date 2018/4/15
 **/
package com.xinaml.jpa.ser;


import com.xinaml.jpa.dto.BaseDTO;
import com.xinaml.jpa.entity.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * 基础业务接口
 *
 * @param <BE>
 * @param <BD>
 */
public interface Ser<BE extends BaseEntity, BD extends BaseDTO> {
    /**
     * 查询所有数据
     *
     * @return
     */
    default List<BE> findAll()  {
        return null;
    }

    /**
     * 查询分页数据
     *
     * @param dto
     * @return
     */
    default Map<String,Object> findByPage(BD dto) {
        return null;
    }

    /**
     * 查询数据量
     *
     * @param dto
     * @return
     */
    default Long count(BD dto)   {
        return null;
    }

    /**
     * 查询第一个对象
     *
     * @param dto
     * @return
     */
    default BE findOne(BD dto)   {
        return null;
    }



    /**
     * 根据条件询对象列表
     * 默认不分页排序
     *
     * @param dto
     * @return
     */
    default List<BE> findByRTS(BD dto)   {
        return null;
    }


    /**
     * 通过id查询某个对象
     *
     * @param id
     * @return
     */
    default BE findById(String id)   {
        return null;
    }


    /**
     * 保存对象列表
     *
     * @param entities
     */
    default void save(BE... entities)   {

    }

    default BE save(BE be)   {
        return null;
    }

    /**
     * 通过id删除对象
     *
     * @param ids
     */
    default void remove(String... ids)   {

    }


    /**
     * 删除对象列表
     *
     * @param entities
     */
    default void remove(BE... entities)   {

    }


    /**
     * 更新对象
     *
     * @param entities
     */
    default void update(BE... entities)   {

    }
    /**
     * 更新对象
     *
     * @param entities
     */
    default void update(List<BE> entities)   {

    }

    /**
     * 通过id查询对象是否存在
     *
     * @param id
     * @return
     */
    default Boolean exists(String id)   {
        return null;
    }

    /**
     * sql查询
     *
     * @param sql    sql语句
     * @param clazz  查询结果映射类
     * @param fields 查询的字段
     * @param
     * @return
     */
    default <T> List<T> findBySql(String sql, Class clazz, String... fields)   {
        return null;
    }

    /**
     * 查询一条数据通过sql
     * @param sql
     * @param clazz
     * @param fields
     * @param <T>
     * @return
     */
    default <T> T findOneBySql(String sql, Class clazz, String... fields)   {
        return null;
    }
    /**
     * sql原生查询,执行结果需要自己解析
     *
     * @param sql sql语句
     * @return
     */
    default List<Object> findBySql(String sql)   {
        return null;
    }


    /**
     * 执行sql语句
     *
     * @param sql
     */
    default void executeSql(String sql)   {
    }

    /**
     * 获取表名
     *
     * @param clazz
     * @return
     */
    default String getTableName(Class clazz)   {
        return null;
    }
}
