package com.wen.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wenfeng on 2017/11/17.
 */
public class MessageHandlerUtil {
    public static MessageInfo convertByteBufToMessageInfo(ByteBuf byteBuf){
        //Integer readableBytes=byteBuf.readableBytes();
        MessageInfo messageInfo=new MessageInfo();
        Integer length=byteBuf.readInt();
        messageInfo.setMessageLength(length);
        messageInfo.setMagicNumber(byteBuf.readInt());
        messageInfo.setId(byteBuf.readLong());
        try {
            byte[] userBytes=new byte[Constant.USERNAME_LENGTH];
            byteBuf.readBytes(userBytes);
            messageInfo.setUsername(new String(userBytes,"UTF-8"));
            byte[] deviceBytes=new byte[length-Constant.FIX_MESSAGE_LENGTH];
            byteBuf.readBytes(deviceBytes);
            messageInfo.setDeviceNo(new String(deviceBytes,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        messageInfo.setMessageType(byteBuf.readByte());
        messageInfo.setBusinessType(byteBuf.readInt());
        messageInfo.setInfo(byteBuf.readInt());
        return messageInfo;
    }

    public static ByteBuf convertMessageInfoToByteBuf(MessageInfo messageInfo){
       Integer length=Constant.FIX_MESSAGE_LENGTH+messageInfo.getDeviceNo().getBytes().length;
        ByteBuf byteBuf= Unpooled.buffer(length);
        byteBuf.writeInt(messageInfo.getMessageLength());
        byteBuf.writeInt(Constant.MAGIC_NUMBER);
        byteBuf.writeLong(messageInfo.getId());
        try {
            byteBuf.writeBytes(messageInfo.getUsername().getBytes("UTF-8"));
            byteBuf.writeBytes(messageInfo.getDeviceNo().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byteBuf.writeByte(messageInfo.getMessageType());
        byteBuf.writeInt(messageInfo.getBusinessType());
        byteBuf.writeInt(messageInfo.getInfo());
        return byteBuf;
    }
    public static Map<Long,Callback> callbackMap=new ConcurrentHashMap<Long,Callback>();
}
