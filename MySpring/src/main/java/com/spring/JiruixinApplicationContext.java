package com.spring;

import com.jiruixin.AppConfig;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JiruixinApplicationContext {

    private Class appConfig;

    // 单例池
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();
    public JiruixinApplicationContext(Class appConfig){
        this.appConfig = appConfig;

        // 解析配置类
        // ComponentScan注解--->扫描路径--->扫描--->Beandefinition--->BeanDefinitionMap
        scan(appConfig);

        for (Map.Entry<String,BeanDefinition> entry : beanDefinitionMap.entrySet()){
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            if (beanDefinition.getScope().equals("singleton")){
                Object bean = createBean(beanName,beanDefinition);
                singletonObjects.put(beanName,bean);
            }
        }

    }


    private void scan(Class appConfig){
        ComponentScan componentScanAnnotation = (ComponentScan)appConfig.getDeclaredAnnotation(ComponentScan.class);
        String path = componentScanAnnotation.value();

        // 扫描
        // 通过类加载器加载路径里面的类

        ClassLoader classLoader = JiruixinApplicationContext.class.getClassLoader();
        URL resource = classLoader.getResource(path.replace(".","/"));
        File file = new File(resource.getFile());
        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (File f : files) {
                String absolutePath = f.getAbsolutePath();
                String fileName = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class"));
                fileName = fileName.replace("\\",".");
                try {
                    Class<?> aClass = classLoader.loadClass(fileName);
                    if (aClass.isAnnotationPresent(Component.class)){
                        // 表示这是一个bean
                        // 解析类---> BeanDefinition

                        if (BeanPostProcessor.class.isAssignableFrom(aClass)){
                            BeanPostProcessor instance = (BeanPostProcessor) aClass.getDeclaredConstructor().newInstance();
                            beanPostProcessorList.add(instance);
                            //instance.
                        }

                        Component componentAnnotation = aClass.getDeclaredAnnotation(Component.class);
                        String beanName = componentAnnotation.value();
                        BeanDefinition beanDefinition = new BeanDefinition();
                        beanDefinition.setClazz(aClass);
                        if (aClass.isAnnotationPresent(Scope.class)){
                            beanDefinition.setScope(aClass.getDeclaredAnnotation(Scope.class).value());


                        }else{
                            beanDefinition.setScope("singleton");
                        }

                        beanDefinitionMap.put(beanName,beanDefinition);

                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }


            }

        }
    }

    private Object createBean(String beanName,BeanDefinition beanDefinition){

        Class clazz = beanDefinition.getClazz();
        Object o = null;
        try {
            o = clazz.getDeclaredConstructor().newInstance();

            // 依赖注入
            for (Field field : clazz.getDeclaredFields()){
                if (field.isAnnotationPresent(Autowired.class)){
                    Object bean = getBean(field.getName());
                    if (bean == null){
                        throw new NullPointerException();
                    }

                    field.setAccessible(true);
                    field.set(o,bean);

                }

            }

            // Aware回调
            if (o instanceof BeanNameAware){
                ((BeanNameAware) o).setBeanName(beanName);
            }

            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                o = beanPostProcessor.postProcessBeforeInitialization(o, beanName);
            }

            // 初始化

            if (o instanceof InitializingBean){
                ((InitializingBean) o).afterPropertiesSet();
            }

            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                o = beanPostProcessor.postProcessAfterInitialization(o, beanName);
            }

            //BeanPostProcessor

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    public Object getBean(String beanName){

        if(beanDefinitionMap.containsKey(beanName)){
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if(beanDefinition.getScope().equals("singleton")){
                return singletonObjects.get(beanName);
            }else{
                return createBean(beanName,beanDefinition);
            }

        }else{
            throw new NullPointerException();
        }

    }
}
