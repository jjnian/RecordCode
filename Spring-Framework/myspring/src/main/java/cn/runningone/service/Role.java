package cn.runningone.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

//implements InstantiationAwareBeanPostProcessor
@Component
//@DependsOn("cn.runningone.service.UserService")
public class Role implements ApplicationListener {

	@Value("${JAVA_HOME}")
	private String name;


	//@Resource
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@PostConstruct // 初始化方法的注解方式  等同与init-method=init
	public void init(){
		System.out.println("调用的PostConstruct");
	}

	@PreDestroy    // 销毁方法的注解方式  等同于destory-method=destory222
	public void destory(){
		System.out.println("调用PreDestroy销毁化方法....");
	}

//	@Override
//	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//		// TODO Auto-generated method stub
//		System.out.println("初始化之前");
//		return bean;
//	}
//
//	/**
//	 * BeanPostProcessor接口中的方法
//	 * 在Bean的自定义初始化方法执行完成之后执行
//	 * Bean对象已经存在了
//	 */
//	@Override
//	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//		System.out.println("初始化之后");
//		return bean;
//	}
//
//	/**
//	 * InstantiationAwareBeanPostProcessor中自定义的方法
//	 * 在方法实例化之前执行  Bean对象还没有
//	 */
//	@Override
//	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
//		System.out.println("实例化之前");
//		return null;
//	}
//
//	/**
//	 * InstantiationAwareBeanPostProcessor中自定义的方法
//	 * 在方法实例化之后执行  Bean对象已经创建出来了
//	 */
//	@Override
//	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
//		System.out.println("实例化之后");
//		return true;
//	}

	public void show(){
		System.out.println("1111111111111");
	}


	@Override
	public void onApplicationEvent(ApplicationEvent event) {

	}
}
