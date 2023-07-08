package cn.runningone.extension;

import cn.runningone.service.Role;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyFactoryBean implements FactoryBean {

	public String name = "jiruixin";
	@Override
	public Object getObject() throws Exception {
		System.out.println("调用MyFactoryBean的getObject");
		return new Role();
	}

	@Override
	public Class<?> getObjectType() {
		System.out.println("调用MyFactoryBean的getObjectType");
		return Role.class;
	}
}
