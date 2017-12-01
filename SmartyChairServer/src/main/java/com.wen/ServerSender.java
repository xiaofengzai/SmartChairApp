package com.wen;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import com.wen.util.MessageHandlerUtil;
import com.wen.util.MessageInfo;


/**
 * Created by wenfeng on 2017/11/17.
 */
public class ServerSender {
    public static ChannelFuture sendMessage(MessageInfo messageInfo){
        Channel channel=(Channel) SmartyServer.userMap.get(messageInfo.getUsername());
        return channel.write(MessageHandlerUtil.convertMessageInfoToByteBuf(messageInfo));
    }
}
