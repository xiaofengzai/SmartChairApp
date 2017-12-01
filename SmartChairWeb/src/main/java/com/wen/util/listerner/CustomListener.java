package com.wen.util.listerner;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 自定义listener
 * 
 * @author wujing
 */
@WebListener
public class CustomListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed");
	}

}
