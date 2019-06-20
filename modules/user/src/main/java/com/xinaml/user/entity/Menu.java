package com.xinaml.user.entity;

import com.xinaml.jpa.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @Author: [lgq]
 * @Date: [19-5-13 上午10:01]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@Entity
@Table(name = "lk_menu")
public class Menu extends BaseEntity {

    @Column(columnDefinition = " VARCHAR(56) COMMENT '菜单编码'")
    private String code;

    @Column(columnDefinition = " VARCHAR(56) COMMENT '父菜单ID'")
    private String pId;

    @Column(columnDefinition = " VARCHAR(56) COMMENT '名称'")
    private String name;

    @Column(columnDefinition = " VARCHAR(56) COMMENT '请求地址'")
    private String url;

    @Column(columnDefinition = "TINYINT COMMENT '菜单排序'")
    private Integer sort;

    @Column(columnDefinition = "TINYINT COMMENT '菜单状态'")
    private Integer status;

    @Column(columnDefinition = " VARCHAR(56) COMMENT '图标'")
    private String icon;

    @Column(nullable = false, columnDefinition = "DATETIME  COMMENT '更新时间'")
    private LocalDateTime updateDate;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

}