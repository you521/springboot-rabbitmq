package com.you.wstro.bean.rabbitmq;

import java.util.Map;

import lombok.Data;

@Data
public class RabbitQueue
{
    /**
     * 队列名
     */
    String name;

    /**
     * 虚拟主机名
     */
    //String vhost;

    /**
     * 是否持久化队列
     */
    boolean durable;

    /**
     * true:队列上没有consumer时，自动删除队列
     */
    boolean autoDelete;

    /**
     * 是否排外
     */
    boolean exclusive;
    
    /**
     * 其他参数
     */
    Map<String, Object> arguments;
}
