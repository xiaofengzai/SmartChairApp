package com.wen.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 门店
 * Created by wenfeng on 2017/12/6.
 */
@Entity
public class Store extends BaseModel{
    @Column(nullable = false)
    private String storeName;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String owner;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
