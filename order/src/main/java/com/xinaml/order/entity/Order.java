package com.xinaml.order.entity;

import com.xinaml.jpa.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lk_order")
public class Order extends BaseEntity {
    @Column(columnDefinition = "VARCHAR(56) COMMENT '订单编号' ")
    private String code;
    @Column(columnDefinition = "VARCHAR(56) COMMENT '订单名' ")
    private String name;
    @Column(columnDefinition = " DECIMAL(10,2) COMMENT '订单金额'")
    private Double price;
    @Column(columnDefinition = " TINYINT COMMENT '购买数量'")
    private Integer count;
    @Column(name = "user_id", nullable = false, length = 36)
    private String userId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
