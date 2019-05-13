package com.xinaml.user.entity;

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
@Table(name = "lk_user")
public class User extends BaseEntity {
    @Column(columnDefinition = " VARCHAR(56) COMMENT '库存'")
    private String username ;
    @Column(columnDefinition = "VARCHAR(56) COMMENT '产品名' ")
    private String password;
    @Column(columnDefinition = "DECIMAL(10,2) COMMENT '单价' ")
    private Double account;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getAccount() {
        return account;
    }

    public void setAccount(Double account) {
        this.account = account;
    }
}
