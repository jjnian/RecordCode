package com.jiruixin.service;

import com.spring.*;

@Component("userService")
@Scope("prototype")
public class UserService implements BeanNameAware,InitializingBean,UserInterface {

    @Autowired
    private RoleService roleService;

    private String beanName;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void getRoleService(){
        System.out.println(roleService);
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean的初始化");
    }
}
