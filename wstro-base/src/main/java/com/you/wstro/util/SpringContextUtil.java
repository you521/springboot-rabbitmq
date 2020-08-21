package com.you.wstro.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 对Spring容器进行各种上下文操作的工具类
 * <p>
 * 该工具类必须声明为Spring 容器里的一个Bean对象，否则无法自动注入ApplicationContext对象
 * <p>
 * 可使用@Component注解实例化，注意要开启包扫描并且所在包路径能被扫描到
 * 
 * @author you
 * @createDate 2020-08-09
 *
 */

@Component
public class SpringContextUtil implements ApplicationContextAware
{

    // spring应用上下文
    private static ApplicationContext applicationContext; 
    
    /*
     * 通过传递applicationContext参数初始化成员变量applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
          SpringContextUtil.applicationContext = applicationContext;       
    }

    // 获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    
    /**
     * 根据Bean名称获取Bean对象
     * 
     * @param name Bean名称
     * @return 对应名称的Bean对象
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }
    
    /**
     * 根据class获取Bean对象
     * 
     * @param name Bean名称
     * @return 对应名称的Bean对象
     */
    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }
 
    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }
    
    /**
     * 判断是否包含对应名称的Bean对象
     * 
     * @param name Bean名称
     * @return 包含：返回true，否则返回false。
     */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }
 
    /** 
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。 
     * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）   
     * @param name 
     * @return boolean 
     * @throws NoSuchBeanDefinitionException 
     */  
    public static boolean isSingleton(String name) {
        return applicationContext.isSingleton(name);
    }

    /**
     * 获取对应Bean名称的类型
     * 
     * @param name Bean名称
     * @return 返回对应的Bean类型
     */
    public static Class<?> getType(String name) {
        return applicationContext.getType(name);
    }
}
