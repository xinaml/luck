package com.xinaml.storage.entity;

import com.xinaml.jpa.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: [lgq]
 * @Date: [19-5-7 上午9:39]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@Entity
@Table(name = "lk_storage")
public class Storage extends BaseEntity {
    @Column(columnDefinition = " TINYINT COMMENT '库存'")
    private Integer count;
    @Column(columnDefinition = "DECIMAL(10,2) COMMENT '单价' ")
    private Double price;
    @Column(columnDefinition = "VARCHAR(56) COMMENT '产品名' ")
    private String name;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
