package cn.runningone.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//implements SmartInitializingSingleton, InitializingBean
@Component
public class UserService{

	private String name;

	@Autowired
	private  Role role;


//	@Override
//	public void afterSingletonsInstantiated() {
//		System.out.println("这是SmartInitializingSingleton.afterSingletonsInstantiated方法");
//	}
//
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		System.out.println("调用InitializingBean.afterPropertiesSet方法");
//	}

	public void setRole(Role role) {
		this.role = role;
	}


	public String show(){
		System.out.println("1111111111111");
		//int a = (1 / 0);
		return "返回值";
	}
}
