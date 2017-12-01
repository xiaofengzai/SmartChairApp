package com.wen.controller;

import com.wen.netty.WebClientSender;
import com.wen.util.Constant;
import com.wen.util.MessageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Api(value = "api接口")
@RestController
@RequestMapping("/api")
public class ApiController {

	@CrossOrigin(origins = "http://localhost:9997")
	@PostMapping(value = "/get")
	public HashMap<String, Object> get(@RequestParam String name) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("title", "hello world");
		map.put("name", name);
		return map;
	}

	@ApiOperation(value = "netty测试")
	@GetMapping("/netty/{info}")
	public void nettyTest(@PathVariable("info") Integer info){
		MessageInfo messageInfo=new MessageInfo((byte) Constant.MESSAGE_REQUEST,Constant.PAY_WEB);
		messageInfo.setUsername(Constant.WEBUSER);
		messageInfo.setDeviceNo(Constant.USERNAME);
		messageInfo.setInfo(info);
		System.out.println("听说不错，"+info+"块钱，试一发");
		WebClientSender.sendMessage(messageInfo);
	}
}
