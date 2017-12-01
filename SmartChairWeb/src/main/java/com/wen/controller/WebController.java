package com.wen.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "web接口")
@Controller
@RequestMapping("/web")
public class WebController {
	
	private static final Logger logger =  LoggerFactory.getLogger(WebController.class);
	
	@PostMapping("index")
	@ApiOperation(value = "index")
	public String index(ModelMap map){
		logger.info("这里是controller");
		map.put("title", "hello world");
		return "index"; // 注意，不要在最前面加上/，linux下面会出错
	}
	
	@PostMapping("error")
	public String error(ModelMap map){
		throw new RuntimeException("测试异常");
	}


}
