package cn.runningone.service;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterAdvice;

public class MyMethodInterceptor implements MethodInterceptor, AfterAdvice {
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("方法的invocation");
		invocation.proceed();
		return null;
	}
}
