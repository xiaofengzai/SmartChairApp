package com.wen.netty;

import com.wen.util.MessageHandlerUtil;
import com.wen.util.MessageInfo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;


/**
 * Created by wenfeng on 2017/11/17.
 */
public class WebClientSender {
    public static ChannelFuture sendMessage(MessageInfo messageInfo){
        Channel channel=SmartyWebClientHandler.channel;
        System.out.println();
        return channel.write(MessageHandlerUtil.convertMessageInfoToByteBuf(messageInfo));
    }
}
