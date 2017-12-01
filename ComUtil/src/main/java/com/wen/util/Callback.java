package com.wen.util;

/**
 * Created by wenfeng on 2017/11/21.
 */
public abstract class Callback {
    private MessageInfo messageInfo;
    public abstract void onRecieve();

    public MessageInfo getMessageInfo() {
        return messageInfo;
    }

    public Callback setMessageInfo(MessageInfo messageInfo) {
        this.messageInfo = messageInfo;
        return this;
    }
}
