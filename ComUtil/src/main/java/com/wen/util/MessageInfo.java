package com.wen.util;

/**
 * Created by wenfeng on 2017/11/14.
 */
public class MessageInfo implements Cloneable{
    /**
     * 消息长度(4字节)
     */
    private int messageLength;
    /**
     * 魔数(4字节)
     */
    private int magicNumber;

    /**
     * 消息ID 8字节
     */
    private long id;

    /**
     * 用户名 IMEI码 15位数字
     */
    private String username;
    /**
     * 设备编号
     */
    private String deviceNo="";
    /**
     * 消息类型（请求/回复）(1字节)
     */
    private byte messageType;
    /**
     * 业务类型(登录、心跳，付款)(4字节)
     */
    private int businessType;
    /**
     * 业务结果（成功，失败，或金额）(4字节)
     */
    private int info=0;

    public MessageInfo(){
    }
    public MessageInfo(byte messageType, int businessType){
        this.magicNumber=Constant.MAGIC_NUMBER;
        this.businessType=businessType;
        this.messageType=messageType;
        this.id=System.currentTimeMillis();
    }

    public int getMessageLength() {
        return messageLength;
    }

    public void setMessageLength(int messageLength) {
        this.messageLength = messageLength;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(Integer magicNumber) {
        this.magicNumber = magicNumber;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
        this.messageLength=deviceNo.getBytes().length+Constant.FIX_MESSAGE_LENGTH;
    }

    public byte getMessageType() {
        return messageType;
    }

    public void setMessageType(byte messageType) {
        this.messageType = messageType;
    }

    public int getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }
    @Override
    public Object clone()  {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {

        }
        return null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMagicNumber(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        this.messageLength=this.deviceNo.getBytes().length+Constant.FIX_MESSAGE_LENGTH;
    }

    public void setBusinessType(int businessType) {
        this.businessType = businessType;
    }
}
