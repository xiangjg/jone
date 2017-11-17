package com.jone.controller.system;

import com.jone.api.system.UserService;
import com.jone.controller.BaseController;
import com.jone.entity.system.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
@EnableAutoConfiguration
@RequestMapping(value="/system")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findByName")
    public void findByName(@RequestParam String userName, HttpServletResponse response) {
        User user = userService.findByUserName(userName);
        printJson(user, response);
    }
}
