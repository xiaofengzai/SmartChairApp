package com.wen.model;

import com.wen.util.UUIDUtil;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by wenfeng on 2017/12/6.
 */
@MappedSuperclass
public class BaseModel {

    @Id
    @Column(nullable = false,length = 50)
    private String id= UUIDUtil.getCurrentUUID();

    @Column(nullable = false)
    private Date createdTime;

    @Column(nullable = false)
    private Date updatedTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
