package com.xinaml.user.entity;

import com.xinaml.jpa.entity.BaseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

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
    @Column(columnDefinition = " VARCHAR(56) COMMENT '用户名'")
    private String username;
    @Column(columnDefinition = "VARCHAR(60) COMMENT '密码' ")
    private String password;
    @Column(columnDefinition = "DECIMAL(10,2) COMMENT '资产' ")
    private Double account;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
           @JoinTable(name="lk_user_role",joinColumns={@JoinColumn(name="user_id",nullable = false)},
                  inverseJoinColumns={@JoinColumn(name="role_id",nullable = false)})
    private Set<Role> roleSet=new LinkedHashSet<>();

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

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

}
