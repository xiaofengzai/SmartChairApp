package com.wen.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 设备
 * Created by wenfeng on 2017/12/6.
 */
@Entity
public class Device extends BaseModel {
    /**
     * 设备IEMI编码
     */
    @Column(nullable = false)
    private String iemi;
    /**
     * 设备静态IP
     */
    @Column(nullable = false)
    private String host;
    @Column(nullable = false)
    /**
     * 设备端口号
     */
    private String port;
    @Column(nullable = false)
    /**
     * 设备安装地点
     */
    private String address;
    /**
     * 设备维护人
     */
    @Column(nullable = false)
    private String owner;
    /**
     * 设备状态
     */
    @Column(nullable = false)
    private Integer status;
    /**
     * 设备型号
     */
    @Column(nullable = false)
    private String modelNumber;

    /**
     * 安装门店ID
     */
    @Column(nullable = false)
    private Integer storeId;

    public String getIemi() {
        return iemi;
    }

    public void setIemi(String iemi) {
        this.iemi = iemi;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
}
