package cn.runningone;

import cn.runningone.config.AppConfig;
import cn.runningone.extension.MyFactoryBean;
import cn.runningone.service.Role;
import cn.runningone.service.UserService;
import cn.runningone.service.UserServiceIn;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args){
		//AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		ClassPathXmlApplicationContext annotationConfigApplicationContext = new ClassPathXmlApplicationContext("spring.xml");

		UserService userService = annotationConfigApplicationContext.getBean(UserService.class);
		//Role bean = annotationConfigApplicationContext.getBean(Role.class);
		//System.out.println(userService instanceof Role);
		//userService.show();
		userService.show();


		//annotationConfigApplicationContext.getBean("myFactoryBean");



	}


}
