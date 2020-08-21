package com.you.wstro.bean.rabbitmq;

import java.util.Map;

import com.you.wstro.bean.Enum.RabbitExchangeTypeEnum;

import lombok.Data;

@Data
public class RabbitExchange
{
    /**
     * 交换机名
     */
    String name;

    /**
     * 虚拟主机名
     */
    //String vhost;

    /**
     * 交换机类型
     */
    RabbitExchangeTypeEnum type;

    /**
     * 是否延迟交换机
     */
    boolean delayed;

    /**
     * 是否持久化
     */
    boolean durable = true;

    /**
     * true：没有队列时，自动删除交换机
     */
    boolean autoDelete;
    /**
     * 其他参数
     */
    Map<String, Object> arguments;
}
