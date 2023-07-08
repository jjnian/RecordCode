package cn.runningone.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;



/**
 * AnnotationPointcut  切面
 * public void before()  public void after()       通知
 * 通知分为  前置 后置  环绕  最终
 * execution(* cn.runningone.service.Role.*(..))   切入点
 * 符合切入点方法的是连接点
 */
@Aspect
@Component
public class MyAnnotationPointcut {


	@Before("execution(* cn.runningone.service.UserService.*(..))")
	public void before(){
		System.out.println("---------方法执行前---------");
	}


	@After("execution(* cn.runningone.service.UserService.*(..))")
	public void after(){
		System.out.println("---------方法执行后---------");
	}

	@AfterReturning(value = "execution(* cn.runningone.service.UserService.*(..))",returning = "res")
	public void afterReturn(JoinPoint jp, String res){
		System.out.println("---------方法执行afterReturn---------");
		System.out.println(res);
	}

	@AfterThrowing(value = "execution(* cn.runningone.service.UserService.*(..))",throwing="ex")
	public void afterThrowing(Exception ex){
		System.out.println("---------------");
		System.out.println(ex.getMessage());
	}

//	@Around("execution(* cn.runningone.service.UserService.*(..))")
//	public Object around(ProceedingJoinPoint pjp){
//
//		System.out.println("---------环绕方法---------");
//		Object o = null;
//		try {
//			o = pjp.proceed();
//		} catch (Throwable throwable) {
//			throwable.printStackTrace();
//		}
//		return o;
//	}
}
