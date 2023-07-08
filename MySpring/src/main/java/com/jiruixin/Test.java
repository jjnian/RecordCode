package com.jiruixin;

import com.jiruixin.service.UserInterface;
import com.jiruixin.service.UserService;
import com.spring.JiruixinApplicationContext;

public class Test {

    public static void main(String[] args) {
        JiruixinApplicationContext jiruixinApplicationContext = new JiruixinApplicationContext(AppConfig.class);
        UserInterface userService = (UserInterface) jiruixinApplicationContext.getBean("userService");
        userService.getRoleService();

    }
}
