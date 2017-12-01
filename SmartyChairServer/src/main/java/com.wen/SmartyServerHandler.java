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
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import com.wen.util.Callback;
import com.wen.util.Constant;
import com.wen.util.MessageHandlerUtil;
import com.wen.util.MessageInfo;


public class SmartyServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
	    throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		if(buf.readableBytes()==0)
			return;
		MessageInfo messageInfo= MessageHandlerUtil.convertByteBufToMessageInfo(buf);
		handleRequest(ctx,messageInfo);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		ctx.close();
    }

    private void handleRequest(ChannelHandlerContext ctx, final MessageInfo messageInfo) {
		String username = messageInfo.getUsername();
		Integer businessType = messageInfo.getBusinessType();
		MessageInfo resp = (MessageInfo) messageInfo.clone();
		resp.setMessageType((byte) Constant.MESSAGE_RESPONSE);
		switch (businessType) {
			case Constant.LOGIN:
				Channel channel = ctx.channel();
				SmartyServer.userMap.put(username, channel);
				System.out.println("服务器收到设备" + messageInfo.getUsername() + "的登录请求");
				resp.setInfo(Constant.MESSAGE_SUCCESS);
				break;
			case Constant.HEART:
				System.out.println("服务器收到设备" + messageInfo.getUsername() + "的心跳请求");
				heartbeatsRequest(ctx, messageInfo);
				return;
			case Constant.EXIT:
				SmartyServer.userMap.remove(username);
				ctx.disconnect();
				return;
			case Constant.PAY:  //设备回复
				// 更新状态及库存
				Channel webChannel=SmartyServer.userMap.get(Constant.WEBUSER);
				resp.setBusinessType(Constant.PAY_WEB);
				resp.setDeviceNo(messageInfo.getUsername());
				System.out.println("Smart Chair:"+messageInfo.getInfo()+"已经开机，通知web 善后");
				webChannel.writeAndFlush(MessageHandlerUtil.convertMessageInfoToByteBuf(resp));
				return;
			case Constant.PAY_WEB:  //WEB后台的支付消息
				// 更新状态及库存
				System.out.println("web 支付完成，通知Smart Chair开始干活:"+messageInfo.getInfo());
				Channel smartChairChannel=SmartyServer.userMap.get(messageInfo.getDeviceNo());
				resp.setBusinessType(Constant.PAY);
				smartChairChannel.writeAndFlush(MessageHandlerUtil.convertMessageInfoToByteBuf(resp));
				return;

		}
		resp.setId(messageInfo.getId()+1L);
		ctx.writeAndFlush(MessageHandlerUtil.convertMessageInfoToByteBuf(resp));
	}

	private void heartbeatsRequest(ChannelHandlerContext ctx,MessageInfo messageInfo){
		MessageInfo resps=(MessageInfo)messageInfo.clone();
		resps.setMessageType((byte) Constant.MESSAGE_RESPONSE);
		Channel channel;
		if(SmartyServer.userMap.containsKey(messageInfo.getUsername())){
			 channel= SmartyServer.userMap.get(messageInfo.getUsername());
			resps.setInfo(Constant.MESSAGE_SUCCESS);
			channel.writeAndFlush(MessageHandlerUtil.convertMessageInfoToByteBuf(resps));
		}else{
			channel=ctx.channel();
			SmartyServer.userMap.put(messageInfo.getUsername(),channel);
			resps.setInfo(Constant.MESSAGE_SUCCESS);
			ctx.writeAndFlush(MessageHandlerUtil.convertMessageInfoToByteBuf(resps));
		}
	}

}
