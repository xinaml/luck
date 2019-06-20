package com.xinaml.order.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @Author: [lgq]
 * @Date: [19-5-7 上午9:39]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StorageVO {
    private String id;
    private int count;
    private Double price;
    private String name;
    private String port;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
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

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
