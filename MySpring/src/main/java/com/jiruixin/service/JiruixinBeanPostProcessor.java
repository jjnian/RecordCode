package com.jiruixin.service;

import com.spring.BeanPostProcessor;
import com.spring.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class JiruixinBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (beanName.equals("userService")){

            ((UserService)bean).setName("纪锐鑫");
        }


        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        //System.out.println("初始化后");
        if (beanName.equals("userService")){
            Object proxyInstance = Proxy.newProxyInstance(JiruixinBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                    System.out.println("代理逻辑");
                    Object invoke = method.invoke(bean, args);
                    return invoke;
                }
            });
            return proxyInstance;
        }
        return bean;
    }
}
