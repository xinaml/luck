package com.xinaml.user.entity;

import com.xinaml.jpa.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @Author: [lgq]
 * @Date: [19-5-13 上午10:01]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@Entity
@Table(name = "lk_role")
public class Role extends BaseEntity {
    @Column(columnDefinition = " VARCHAR(56) COMMENT '角色名'")
    private String name;


    @Column(nullable = false, columnDefinition = "DATETIME  COMMENT '更新时间'")
    private LocalDateTime updateDate;

    @Column(columnDefinition = "TINYINT COMMENT '状态'")
    private Integer status;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "lk_role_menu", joinColumns = {@JoinColumn(name = "role_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "menu_id", nullable = false)})
    private Set<Menu> menuSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<Menu> getMenuSet() {
        return menuSet;
    }

    public void setMenuSet(Set<Menu> menuSet) {
        this.menuSet = menuSet;
    }

}