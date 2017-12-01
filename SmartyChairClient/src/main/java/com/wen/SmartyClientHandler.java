/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.wen;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.Date;
import java.util.logging.Logger;
import com.wen.util.Constant;
import com.wen.util.MessageHandlerUtil;
import com.wen.util.MessageInfo;


public class SmartyClientHandler extends ChannelHandlerAdapter {

    private static final Logger logger = Logger
	    .getLogger(SmartyClientHandler.class.getName());

    private int currentTime = 0;

    /**
     * Creates a client-side handler.
     */
    public SmartyClientHandler() {


    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        MessageInfo messageInfo=new MessageInfo((byte)Constant.MESSAGE_REQUEST, Constant.LOGIN);
        messageInfo.setUsername(Constant.USERNAME);
        System.out.println("设备"+Constant.USERNAME+"发起登录请求");
	    ctx.writeAndFlush(MessageHandlerUtil.convertMessageInfoToByteBuf(messageInfo));
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("心跳触发时间："+new Date());
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.WRITER_IDLE) {
                System.out.println("client heartbeats currentTime:"+currentTime);
                currentTime++;
                MessageInfo messageInfo=new MessageInfo((byte)Constant.MESSAGE_REQUEST, Constant.HEART);
                messageInfo.setUsername(Constant.USERNAME);
                ctx.channel().writeAndFlush(MessageHandlerUtil.convertMessageInfoToByteBuf(messageInfo));
            }
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
	    throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        if(buf.readableBytes()==0)
            return;
        MessageInfo messageInfo= MessageHandlerUtil.convertByteBufToMessageInfo(buf);
        handleMessageFromServer(ctx,messageInfo);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 释放资源
        logger.warning("Unexpected exception from downstream : "
            + cause.getMessage());
        MessageInfo messageInfo=new MessageInfo((byte)Constant.MESSAGE_REQUEST, Constant.EXIT);
        messageInfo.setUsername(Constant.USERNAME);
        ctx.writeAndFlush(MessageHandlerUtil.convertMessageInfoToByteBuf(messageInfo));
        ctx.disconnect();
        ctx.close();
    }
    private void handleMessageFromServer(ChannelHandlerContext ctx,MessageInfo messageInfo){
        MessageInfo resp=(MessageInfo)messageInfo.clone();
        resp.setMessageType((byte)Constant.MESSAGE_RESPONSE);
        switch (messageInfo.getBusinessType()){
            case Constant.LOGIN:
                System.out.println("服务器告诉我"+messageInfo.getUsername()+"登录成功啦，哈哈哈");
                break;
            case Constant.PAY:
                System.out.println("已收到付款"+messageInfo.getInfo()+",开始干活啦");
                resp.setInfo(Constant.MESSAGE_SUCCESS);
                resp.setId(messageInfo.getId()+1L);
                ctx.writeAndFlush(MessageHandlerUtil.convertMessageInfoToByteBuf(resp));
        }
    }

}
