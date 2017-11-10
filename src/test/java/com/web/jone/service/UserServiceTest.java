package com.web.jone.service;

import com.jone.api.system.UserService;
import com.jone.dao.system.UserDao;
import com.jone.entity.system.User;
import com.web.jone.JoneApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JoneApplication.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @Test
    public void initData() throws Exception {
        User user = new User();
        user.setUserName("liwei");
        User user1 = userDao.save(user);
        System.out.println(user1);
    }


    @Test
    public void findByUserName() throws Exception {
        User user = userService.findByUserName("管理员");
        System.out.println(user);

    }
}
