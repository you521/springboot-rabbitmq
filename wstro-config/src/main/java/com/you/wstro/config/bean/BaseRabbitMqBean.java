package com.you.wstro.config.bean;



import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * RabbitMq实体类
 * @author Administrator
 *
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class BaseRabbitMqBean
{
    // rabbitmq服务器地址
    private String host;
    
    // rabbitmq服务器端口号
    private int port;
    
    // rabbitmq登录名称
    private String userName;
 
    // rabbitmq登录密码
    private String passWord;
 
    // rabbitmq虚拟主机
    private String virtualHost;
    
    // 连接超时，单位毫秒，0表示无穷大，不超时
    private int connectionTimeout;
    
    // 消息确认机制 --- 是否启用发布确认(默认false），从生产者-->交换器
    private boolean publisherConfirms;
    
    // 消息确认机制 --- 是否启用发布返回（默认false），从交换器-->队列
    private boolean publisherReturns;
    
    // rabbitmq缓存配置
    private RabbitMqCache cache;
}
