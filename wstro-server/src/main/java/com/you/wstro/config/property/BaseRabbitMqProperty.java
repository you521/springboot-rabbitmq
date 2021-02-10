package com.you.wstro.config.property;



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
public class BaseRabbitMqProperty
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
    
    // rabbitmq缓存配置
    private RabbitMqCache cache;
}
