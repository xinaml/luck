/**
 * 基础业务
 *
 * @author lgq
 * @date 2018/4/15
 **/
package com.xinaml.jpa.ser;


import com.xinaml.common.exception.RepException;
import com.xinaml.jpa.dao.JapRep;
import com.xinaml.jpa.dao.JpaSpec;
import com.xinaml.jpa.dto.BaseDTO;
import com.xinaml.jpa.entity.BaseEntity;
import com.xinaml.jpa.uitls.ClazzTypeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Table;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ServiceImpl<BE extends BaseEntity, BD extends BaseDTO> implements Ser<BE, BD>, Serializable {
    @Autowired(required = false)
    protected JapRep<BE, BD> rep;
    @Autowired
    protected EntityManager entityManager;


    @Override
    public List<BE> findAll() {
        return rep.findAll();
    }

    @Override
    public Map<String, Object> findByPage(BD dto) {
        JpaSpec jpaSpec = new JpaSpec<BE, BD>(dto);
        PageRequest page = jpaSpec.getPageRequest(dto);
        Page<BE> rs = rep.findAll(jpaSpec, page);
        Map map = new HashMap<String, Object>(2);
        map.put("rows", rs.getContent());
        map.put("total", rs.getTotalElements());
        return map;
    }

    @Override
    public Long count(BD dto) {
        JpaSpec jpaSpec = new JpaSpec<BE, BD>(dto);
        return rep.count(jpaSpec);
    }

    @Override
    public BE findOne(BD dto) {
        JpaSpec jpaSpec = new JpaSpec<BE, BD>(dto);
        Optional<BE> op = rep.findOne(jpaSpec);
        if (op.isPresent()) {
            return op.get();
        }
        return null;
    }


    @Override
    public List<BE> findByRTS(BD dto) {

        JpaSpec jpaSpec = new JpaSpec<BE, BD>(dto);
        if (dto.getSorts().size() > 0) {
            return rep.findAll(jpaSpec, jpaSpec.getSort(dto.getSorts()));
        } else {
            return rep.findAll(jpaSpec);
        }

    }

    @Override
    public BE findById(String id) {
        if (StringUtils.isNotBlank(id)) {
            Optional<BE> optional = rep.findById(id);
            if (null != optional) {
                return optional.get();
            } else {
                return null;
            }
        } else {
            throw new RepException("查询 id 不能为空！");
        }

    }

    @Override
    public void save(BE... entities) {
        rep.saveAll(Arrays.asList(entities));

    }

    @Override
    public BE save(BE be) {
        return rep.save(be);
    }

    @Override
    public void remove(String... ids) {

        if (null != ids && ids.length > 0) {
            for (String id : ids) {
                rep.deleteById(id);
            }
        } else {
            throw new RepException("删除 id 不能为空！");
        }


    }

    @Override
    public void remove(BE... entities) {
        rep.deleteAll(Arrays.asList(entities));

    }

    @Override
    public void update(List<BE> entities) {
        rep.saveAll(entities);
    }

    @Override
    public void update(BE... entities) {
        for (BE be : entities) {
            rep.save(be);
        }

    }

    @Override
    public Boolean exists(String id) {
        return rep.existsById(id);

    }


    @Override
    public List<Object> findBySql(String sql) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        return nativeQuery.getResultList();


    }

    @Override
    public <T> T findOneBySql(String sql, Class clazz, String... fields) {
        List<T> objects = findBySql(sql, clazz, fields);
        if (objects.size() > 0) {
            return objects.get(0);
        } else {
            return null;
        }
    }

    @Override
    public <T> List<T> findBySql(String sql, Class clazz, String... fields) {
        List<Field> all_fields = new ArrayList<>(); //源类属性列表
        Class temp_clazz = clazz;
        while (null != temp_clazz) { //数据源类所有属性（包括父类）
            all_fields.addAll(Arrays.asList(temp_clazz.getDeclaredFields()));
            temp_clazz = temp_clazz.getSuperclass();
            if (Object.class.equals(temp_clazz) || null == temp_clazz) {
                break;
            }
        }
        Query nativeQuery = entityManager.createNativeQuery(sql);
        List<Object> resultList = nativeQuery.getResultList();
        List<T> list = new ArrayList<>(resultList.size());

        //解析查询结果
        try {
            for (int i = 0; i < resultList.size(); i++) {
                Object[] arr_obj;
                if (fields.length > 1) {
                    arr_obj = (Object[]) resultList.get(i);
                } else {
                    arr_obj = new Object[]{resultList.get(i)};
                }
                Object obj = clazz.newInstance();
                for (int j = 0; j < fields.length; j++) {
                    for (Field field : all_fields) {
                        if (field.getName().equals(fields[j])) {
                            field.setAccessible(true);
                            if (!field.getType().isEnum()) { //忽略枚举类型
                                field.set(obj, ClazzTypeUtil.convertDataType(field.getType().getSimpleName(), arr_obj[j]));
                            } else {
                                Field[] enumFields = field.getType().getFields();
                                for (int k = 0; k < enumFields.length; k++) {
                                    Integer val = null;
                                    if (null != arr_obj[j]) {
                                        val = Integer.parseInt(arr_obj[j].toString());
                                    }
                                    if (null != val && val == k) {
                                        String name = enumFields[k].getName();
                                        field.set(obj, field.getType().getField(name).get(name));
                                    }
                                }
                            }
                            break;
                        }
                    }
                }
                list.add((T) obj);
            }
        } catch (Exception e) {
            throw new RepException(e.getMessage());
        }

        return list;
    }

    @Transactional
    @Override
    public void executeSql(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public String getTableName(Class clazz) {
        try {
            if (clazz.isAnnotationPresent(Table.class)) {
                Annotation annotation = clazz.getAnnotation(Table.class);
                Method[] methods = annotation.annotationType().getMethods();
                for (Method method : methods) {
                    if (!method.isAccessible()) {
                        method.setAccessible(true);
                    }
                    if ("name".equals(method.getName())) {
                        Object invoke = method.invoke(annotation);
                        return invoke.toString();
                    }
                }
            }
        } catch (Exception e) {
            throw new RepException(e.getMessage());
        }
        String msg = "解析表名错误！";
        throw new RepException(msg);
    }

}
