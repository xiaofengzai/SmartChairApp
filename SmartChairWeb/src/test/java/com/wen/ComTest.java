package com.wen;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.wen.model.QUser;
import com.wen.model.User;
import com.wen.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by wenfeng on 2017/12/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SmartChairWebApplication.class)
public class ComTest {
    @Autowired
    private UserRepository userRepository;
    private QUser qUser=QUser.user;


    @Test
    public void save(){
//        User user=new User();
//        user.setNickname("wenfeng");
//        user.setUsername("dlld");
//        user.setEmail("ddd");
//        user.setCreatedTime(new Date());
//        user.setUpdatedTime(new Date());
//        userRepository.save(user);
        User user1= userRepository.findOne(qUser.email.eq("ddd"));
        System.out.println(user1.getNickname());
    }
}
