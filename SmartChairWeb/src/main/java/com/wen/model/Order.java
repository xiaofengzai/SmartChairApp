package com.wen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Date;

/**
 * 订单
 * Created by wenfeng on 2017/12/6.
 */
@Entity
public class Order extends BaseModel {
    /**
     * 订单编号
     */
    @Column(nullable = false)
    private String orderNo;
    /**
     * 设备ID
     */
    @Column(nullable = false)
    private String deviceId;
    /**
     * 开始使用时间
     */
    @Column(nullable = false)
    private Date startTime;
    /**
     * 结束使用时间
     */
    @Column(nullable = false)
    private Date endTime;
    /**
     * 订单总金额
     */
    @Column(nullable = false)
    private Double totalCost;
    /**
     * 实付金额
     */
    @Column(nullable = false)
    private Double realCost;
    /**
     * 折扣金额
     */
    @Column(nullable = false)
    private Double discount;
    /**
     * 折扣支付方式
     */
    @Column(nullable = false)
    private Integer payType;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getRealCost() {
        return realCost;
    }

    public void setRealCost(Double realCost) {
        this.realCost = realCost;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}
