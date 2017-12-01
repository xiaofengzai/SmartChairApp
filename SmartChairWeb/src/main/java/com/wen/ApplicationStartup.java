package com.wen;

import com.wen.netty.SmartyClient;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by wenfeng on 2017/8/28.
 */
@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        new Thread(new SmartyClient()).start();
    }
}